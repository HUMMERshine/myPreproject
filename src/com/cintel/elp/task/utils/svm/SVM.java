package com.cintel.elp.task.utils.svm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Jama.Matrix;
import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

public class SVM {
	public static double getResult(String localpath, List<Double> test_list) {
		double res = 0;
		
		// -------------------------------------获取训练数据------------------------------------
		List<Double> label = new ArrayList<Double>();
		List<svm_node[]> nodeSet = new ArrayList<svm_node[]>();
		// 前6个为数据x放到nodeset，最后为label目标值y放到label
		getData(nodeSet, label, localpath + "file/data.txt");
		// 将数据表M*N个数据存入datas中
		int dataRange = nodeSet.get(0).length;
		svm_node[][] datas = new svm_node[nodeSet.size()][dataRange]; // 训练集的向量表
		for (int i = 0; i < datas.length; i++) {
			for (int j = 0; j < dataRange; j++) {
				datas[i][j] = nodeSet.get(i)[j];
			}
		}
		// 将label存到数组labels中
		double[] lables = new double[label.size()]; // a,b 对应的lable
		for (int i = 0; i < lables.length; i++) {
			lables[i] = label.get(i);
		}

		// ---------------------------------------获取测试数据-------------------------------------
		List<Double> testlabel = new ArrayList<Double>();
		List<svm_node[]> testnodeSet = new ArrayList<svm_node[]>();

		//getData(testnodeSet, testlabel, localpath + "file/test.txt");
		
//		List<Double> test_list = new ArrayList<>();
//		test_list.add(8.0);
//		test_list.add(1.0);
//		test_list.add(8.0);
//		test_list.add(7.0);
//		test_list.add(213000.0);
//		test_list.add(75.0);
//		test_list.add(11078.0);
//		test_list.add(1.0);
//		test_list.add(1690.0);
		
		getData2(testnodeSet, testlabel, test_list);
		// 把测试数据放到testdatas中
		svm_node[][] testdatas = new svm_node[testnodeSet.size()][dataRange]; // 训练集的向量表
		for (int i = 0; i < testdatas.length; i++) {
			for (int j = 0; j < dataRange; j++) {
				testdatas[i][j] = testnodeSet.get(i)[j];
			}
		}
		// 将testlabel 放到testlables中
		double[] testlables = new double[testlabel.size()]; // a,b 对应的lable
		for (int i = 0; i < testlables.length; i++) {
			testlables[i] = testlabel.get(i);
		}
		// ---------------------------------------对
		// 训练、测试数据处理----------------------------

		// 将训练数据和测试数据合成一个表dataList(n1+n2)*m
		svm_node[][] dataList = Adddata(datas, testdatas, dataRange);

		// 主成分分析法对数据进行再处理(n1+n2)*m
		svm_node[][] pcadata = PCA(dataList);

		String f = localpath + "file/表4-5(lable还未处理).txt";
		Write(pcadata, Addlable(lables, testlables), f);

		// 归一法对数据进行处理(n1+n2)*m
		svm_node[][] normaldata = Normalization(pcadata, Max(pcadata), Min(pcadata));
		String w = localpath + "file/表4-6(lable还未处理).txt";
		Write(normaldata, Addlable(lables, testlables), w);

		// 将数据表拆分成 训练数据data n1*m 和 测试数据testdata n2*m，行数不变，列数和主成分一样
		svm_node[][] data = new svm_node[datas.length][normaldata[0].length];
		svm_node[][] testdata = new svm_node[testdatas.length][normaldata[0].length];

		for (int i = 0; i < datas.length; i++)
			for (int j = 0; j < normaldata[0].length; j++) {
				data[i][j] = normaldata[i][j];
			}
		for (int i = datas.length, a = 0; i < normaldata.length; i++, a++)
			for (int j = 0; j < normaldata[0].length; j++) {
				testdata[a][j] = normaldata[i][j];
			}

		// ------------------------------------对
		// lable值进行处理------------------------------

		// 将lables和testlables整合在一起labelList
		double[] lableList = Addlable(lables, testlables);
		// 进行标准化处理
		double[] Standardlable = Standardlizer(lableList);
		// 进行归一化处理
		double Max = Max(Standardlable);// 放lable的最大值，后面反归一需要用到
		double Min = Min(Standardlable);// 放lable的最小值，后面反归一需要用到
		double[] Normallable = Normalization(Standardlable, Max, Min);

		// 处理后的Standardlable拆成训练labels，测试testlables

		for (int i = 0; i < lables.length; i++) {
			lables[i] = Normallable[i];
		}
		for (int i = lables.length, a = 0; i < Normallable.length; i++, a++) {
			testlables[a] = Normallable[i];
		}
		// ----------------------------------保存新数据--------------------------------------
		// 将处理过的数据保存，用来寻找最优参数
		String f1 = localpath + "file/data2(lable和data都处理完).txt";
		String f2 = localpath + "file/test2(lable和data都处理完).txt";
		Write(data, lables, f1);
		Write(testdata, testlables, f2);

		// -------------------------------------训练模型---------------------------------------
		// 定义svm_problem对象
		svm_problem problem = new svm_problem();
		problem.l = nodeSet.size(); // 向量个数
		problem.x = data; // 训练集向量表
		problem.y = lables; // 对应的lable数组
		// ???????????????????????????????????????????????????????????????????????
		// 定义svm_parameter对象
		svm_parameter param = new svm_parameter();
		param.svm_type = svm_parameter.EPSILON_SVR;
		param.kernel_type = svm_parameter.RBF;// LINEAR;
		param.C = 32;
		param.gamma = 0.125;
		param.cache_size = 100;

		param.eps = 0.001;
		// ???????????????????????????????????????????????????????????????????????????

		// 训练SVM分类模型
		System.out.println(svm.svm_check_parameter(problem, param));
		// 如果参数没有问题，则svm.svm_check_parameter()函数返回null,否则返回error描述。

		svm_model model = svm.svm_train(problem, param);
		// svm.svm_train()训练出SVM分类模型

		// -------------------------------------进行测试---------------------------------------
		// 预测测试数据的lable
		double err = 0.0;
		for (int i = 0; i < testdata.length; i++) {

			// 输出实际值
			double truevalue = testlables[i];
			truevalue = oppNormalization(testlables[i], Max, Min);// 反归一
			System.out.print(truevalue + "   ");

			// 输出预测值
			double predictValue = svm.svm_predict(model, testdata[i]);
			predictValue = oppNormalization(predictValue, Max, Min);// 反归一
			System.out.println(predictValue + "   误差： " + Math.abs(predictValue - truevalue));
			res = predictValue;
			// 计算标准误差

			err += (Math.abs(predictValue - truevalue)) * (Math.abs(predictValue - truevalue)) / testdata.length;
		} // for
		err = Math.sqrt(err);
		// 输出误差值
		System.out.println("均方误差err=" + err / data.length);
		
		return res;

	}// --------main

	// -----------------------------------------------------------------------------------

	// 将txt中的数据存放到向量，和label中，最后一个为label
	public static void getData(List<svm_node[]> nodeSet, List<Double> label, String filename) {
		try {
			FileReader fr = new FileReader(new File(filename));
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] datas = line.split(",");
				svm_node[] vector = new svm_node[datas.length - 1];
				for (int i = 0; i < datas.length - 1; i++) {
					svm_node node = new svm_node();
					node.index = i + 1;
					node.value = Double.parseDouble(datas[i]);
					vector[i] = node;
				}
				nodeSet.add(vector);
				double lablevalue = Double.parseDouble(datas[datas.length - 1]);
				label.add(lablevalue);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getData2(List<svm_node[]> nodeSet, List<Double> label, List<Double> list) {
		svm_node[] vector = new svm_node[list.size() - 1];
		for (int i = 0; i < list.size()-1; i++) {
			svm_node node = new svm_node();
			node.index = i + 1;
			node.value = list.get(i);
			vector[i] = node;
		}
		nodeSet.add(vector);
		double lablevalue = list.get(list.size()-1);
		label.add(lablevalue);
	}

	/**
	 * 整合连个矩阵
	 * 
	 * @param datas训练数据n1*m
	 * @param testdatas测试数据n2*m
	 * @param dataRange矩阵总列数
	 * @return 整合后的矩阵n*m
	 */
	// 把训练数据和测试数据合成一个表
	public static svm_node[][] Adddata(svm_node[][] datas, svm_node[][] testdatas, int dataRange) {
		svm_node[][] result = new svm_node[datas.length + testdatas.length][dataRange];
		for (int i = 0; i < datas.length; i++)
			for (int j = 0; j < dataRange; j++) {
				result[i][j] = datas[i][j];
			}
		for (int i = datas.length, a = 0; i < result.length; i++, a++)
			for (int j = 0; j < dataRange; j++) {
				result[i][j] = testdatas[a][j];
			}

		return result;
	}

	public static double[] Max(svm_node[][] dataList) {
		double[] Max = new double[dataList[0].length];// 放j列的最大值

		// 找出数据每列的最大值
		for (int j = 0; j < dataList[0].length; j++) {// 列
			Max[j] = dataList[0][j].value;
			for (int i = 0; i < dataList.length; i++) {// 行
				if (dataList[i][j].value > Max[j])
					Max[j] = dataList[i][j].value;
			}
		}

		return Max;
	}

	public static double Max(double[] lable) {
		double Max = lable[0];// 最大值
		// 找出lable最大值
		for (int i = 0; i < lable.length; i++) {//
			if (lable[i] > Max)
				Max = lable[i];
		}
		return Max;
	}

	public static double Min(double[] lable) {

		double Min = lable[0];// 最小值
		// 找出lable最小值
		for (int i = 0; i < lable.length; i++) {//
			if (lable[i] < Min)
				Min = lable[i];
		}
		return Min;
	}

	public static double[] Min(svm_node[][] dataList) {
		double[] Min = new double[dataList[0].length];// 放j列的最小值
		// 找出数据每列的最小值
		for (int j = 0; j < dataList[0].length; j++) {// 列
			Min[j] = dataList[0][j].value;
			for (int i = 0; i < dataList.length; i++) {// 行
				if (dataList[i][j].value < Min[j])
					Min[j] = dataList[i][j].value;
			}
		}
		return Min;
	}

	/**
	 * 归一化处理 n*m
	 * 
	 * @param dataList整合后的矩阵
	 * @return 归一矩阵
	 */
	public static svm_node[][] Normalization(svm_node[][] dataList, double[] Max, double[] Min) {
		svm_node[][] result = new svm_node[dataList.length][dataList[0].length];
		result = dataList;
		// 按列 对数据进行处理Xj=(Xj-minXj)/(maxXj-minXj)
		for (int j = 0; j < result[0].length; j++) {
			for (int i = 0; i < result.length; i++) {
				result[i][j].value = (result[i][j].value - Min[j]) / (Max[j] - Min[j]);
			}
		}
		return result;
	}

	/**
	 * 将原始数据标准化 n*m
	 * 
	 * @param x转置后的矩阵
	 * @return 标准矩阵
	 */
	public static double[][] Standardlizer(double[][] x) {
		int n = x.length; // 二维矩阵的行号
		int p = x[0].length; // 二维矩阵的列号
		double[] average = new double[p]; // 每一列的平均值
		double[][] result = new double[n][p]; // 标准化后的向量
		double[] var = new double[p]; // 方差
		// 取得每一列的平均值
		for (int k = 0; k < p; k++) {
			double temp = 0;
			for (int i = 0; i < n; i++) {
				temp += x[i][k];
			}
			average[k] = temp / n;
		}
		// 取得方差
		for (int k = 0; k < p; k++) {
			double temp = 0;
			for (int i = 0; i < n; i++) {
				temp += (x[i][k] - average[k]) * (x[i][k] - average[k]);
			}
			var[k] = temp / (n - 1);
		}
		// 获得标准化的矩阵
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				result[i][j] = (double) ((x[i][j] - average[j]) / Math.sqrt(var[j]));
			}
		}
		return result;

	}

	/**
	 * 计算样本相关系数矩阵m*m
	 * 
	 * @param x
	 *            处理后的标准矩阵
	 * @return 系数矩阵
	 */

	public static double[][] CoefficientOfAssociation(double[][] x) {
		int n = x.length; // 二维矩阵的行号
		int p = x[0].length; // 二维矩阵的列号
		double[][] result = new double[p][p];// 相关系数矩阵
		double[] aver = new double[p];// 放每列的平均值
		for (int j = 0; j < p; j++) {
			aver[j] = 0;
			for (int i = 0; i < n; i++) {
				aver[j] += x[i][j] / n;
			}
		}

		for (int i = 0; i < p; i++)
			for (int j = 0; j < p; j++) {
				double a = 0;// 分子
				double b = 0, c = 0;// 分母
				for (int k = 0; k < n; k++) {
					a += (x[k][i] - aver[i]) * (x[k][j] - aver[j]);
					b += (x[k][i] - aver[i]) * (x[k][i] - aver[i]);
					c += (x[k][j] - aver[j]) * (x[k][j] - aver[j]);

				}
				result[i][j] = a / Math.sqrt(b * c);
			}
		return result;
	}

	/**
	 * 计算相关系数矩阵的特征值m*m
	 * 
	 * @param x
	 *            相关系数矩阵
	 * @return 矩阵特征向量
	 */
	public static double[][] FlagValue(double[][] x) {
		// 定义一个矩阵
		Matrix A = new Matrix(x);
		// 由特征值组成的对角矩阵
		Matrix B = A.eig().getD();
		double[][] result = B.getArray();
		return result;

	}

	/**
	 * 计算相关系数矩阵的特征向量m*m
	 * 
	 * @param x
	 *            相关系数矩阵
	 * @return 矩阵特征向量
	 */
	public static double[][] FlagVector(double[][] x) {
		// 定义一个矩阵
		Matrix A = new Matrix(x);
		// 由特征向量组成的对角矩阵
		Matrix B = A.eig().getV();
		double[][] result = B.getArray();
		return result;

	}

	/**
	 * 假设阈值是90%，选取最大的前几个
	 * 
	 * @param x
	 *            特征值
	 * @return 选取前N个主成分
	 */
	public static int SelectPrincipalComponent(double[][] x) {
		int n = x.length; // 二维矩阵的行号,列号
		double[] a = new double[n];
		double A = 0;// 特征值的和

		// 取出特征值放入a[i]中
		for (int i = 0; i < n; i++) {
			a[i] = x[i][i];
			A += a[i];
		}
		// 对特征值进行从大到小排序：a0>a1>a2>a3...
		Arrays.sort(a);// 升序
		double t;
		for (int i = 0; i < a.length / 2; i++)// 降序
		{
			t = a[i];
			a[i] = a[a.length - 1 - i];
			a[a.length - 1 - i] = t;
		}
		int N = a.length;// 取前i个主成分
		double per = 0;// 累计贡献率
		for (int i = 0; i < a.length; i++) {
			if (per >= 0.9) {
				N = i;
				break;// 贡献率达90%
			}
			per += a[i] / A;
		}
		return N;
	}

	/**
	 * 取得主成分
	 * 
	 * @param x
	 *            特征向量m*m--主成分系数
	 * @param A
	 *            特征值m*m
	 * @param l
	 *            选取的主成分
	 * @param z
	 *            标准矩阵n*m
	 * @return 主成分矩阵n*z
	 */
	public static double[][] PrincipalComponent(double[][] x, double[][] A, double[][] z, int l) {
		int n = z.length;// 行数和原矩阵一致,样本个数
		int m = x.length;// 列数，和因素个数一致
		double[][] result = new double[n][l];
		double[][] e = new double[m][l];
		double[] a = new double[n];// 放特征值

		// 取出特征值放入a[i]中
		for (int i = 0; i < m; i++)
			a[i] = A[i][i];
		int[] flag = new int[l];// 标记顺序

		// 对特征值进行排序，取前l，生成相对应的特征向量矩阵e
		// 标记前L个特征值的位置，按顺序放好
		for (int i = 0; i < l; i++) {
			double temp = a[0];
			for (int j = 0; j < m; j++) {
				if (a[j] > temp) {
					flag[i] = j;// 标记最大值
					temp = a[j];
				}
			}
			a[flag[i]] = -999999999;
		}

		// 生成相对应的特征向量矩阵e
		System.out.println("---------------------------前六个主成分系数:\n");
		for (int j = 0; j < l; j++) {
			System.out.println("第" + (j + 1) + "列：");
			for (int i = 0; i < m; i++) {
				e[i][j] = x[i][flag[j]];
				if (j == 0 || j == 1 || j == 4 || j == 5)
					e[i][j] = -e[i][j];
				System.out.println(e[i][j]);
			}
			System.out.println("\n");
		}

		// 主成分矩阵的第i行，j列是=e1j*zi1+e2j*zi2+e3j*zi3+e4j*zi4..(e的j列*z的i行)
		for (int i = 0; i < n; i++)
			for (int j = 0; j < l; j++) {
				result[i][j] = 0;
				for (int k = 0; k < m; k++) {

					result[i][j] += e[k][j] * z[i][k];

				}
			}

		return result;
	}

	// 将非主成分部分删除
	public static svm_node[][] Delete(svm_node[][] datas, double[][] pcadata) {
		svm_node[][] result = new svm_node[pcadata.length][pcadata[0].length];// 取前N个主成分

		for (int i = 0; i < pcadata.length; i++)
			for (int j = 0; j < pcadata[0].length; j++) {
				datas[i][j].value = pcadata[i][j];
			}

		for (int i = 0; i < pcadata.length; i++)
			for (int j = 0; j < pcadata[0].length; j++) {
				result[i][j] = datas[i][j];
			}
		return result;

	}

	// 主成分分析法对数据进行处理
	public static svm_node[][] PCA(svm_node[][] datas) {
		double[][] data = new double[datas.length][datas[0].length];
		for (int i = 0; i < datas.length; i++)
			for (int j = 0; j < datas[0].length; j++) {
				data[i][j] = datas[i][j].value;
			}

		// 标准化矩阵
		double[][] Standard = Standardlizer(data);

		// 相关系数矩阵
		double[][] Assosiation = CoefficientOfAssociation(Standard);

		// 求相关系数矩阵的特征值
		double[][] FlagValue = FlagValue(Assosiation);

		// 求相关系数矩阵的特征向量
		double[][] FlagVector = FlagVector(Assosiation);

		// 获取前N个主成分
		int Select = SelectPrincipalComponent(FlagValue);

		// 生成主成分矩阵
		double[][] pcadata = PrincipalComponent(FlagVector, FlagValue, Standard, Select);
		System.out.println("(m,n):(行数" + pcadata.length + ",列数" + pcadata[0].length + ")");
		System.out.println("主成分矩阵生成完毕...\n");
		System.out.println("-----------------------------主成分得分\n");
		for (int i = 0; i < pcadata.length; i++) {
			System.out.println("第" + (i + 1) + "行：");
			for (int j = 0; j < pcadata[0].length; j++)
				System.out.println(pcadata[i][j]);
			System.out.println("\n");
		}

		// 把非主成分数据删除
		svm_node[][] Result = new svm_node[datas.length][Select];
		Result = Delete(datas, pcadata);

		// 返回主成分数据矩阵
		return Result;

	}

	// 将训练lable和测试lable整合成一个
	public static double[] Addlable(double[] lables, double[] testlables) {
		double[] lableList = new double[lables.length + testlables.length];

		for (int i = 0; i < lables.length; i++) {
			lableList[i] = lables[i];
		}

		for (int i = lables.length, a = 0; i < lableList.length; i++, a++) {
			lableList[i] = testlables[a];
		}
		return lableList;
	}

	// 对lable进行归一化
	public static double[] Normalization(double[] lable, double Max, double Min) {

		double[] result = new double[lable.length];
		result = lable;

		// 按列 对lable进行处理Xj=(Xj-minXj)/(maxXj-minXj)
		for (int i = 0; i < result.length; i++) {
			result[i] = (result[i] - Min) / (Max - Min);
		}
		return result;
	}

	// 对数据进行反归一
	public static double oppNormalization(double lable, double Max, double Min) {

		double result;

		// 对lable进行处理Xj=Xj*(maxXj-minXj)+minXj
		result = lable * (Max - Min) + Min;

		return result;
	}

	// 对lable进行标准化
	public static double[] Standardlizer(double[] lable) {
		double aver = 0;// 平均值
		double s = 0;// 标准差
		for (int i = 0; i < lable.length; i++) {
			aver += (lable[i] / lable.length);
		}
		for (int i = 0; i < lable.length; i++) {
			s += ((lable[i] - aver) * (lable[i] - aver) / (lable.length - 1));
		}
		s = Math.sqrt(s);
		return lable;
	}

	// 将新数据保存到txt中
	public static void Write(svm_node[][] datas, double[] lables, String f) {
		try {
			File file = new File(f);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String conten;
			int n;
			for (int i = 0; i < datas.length; i++) {
				double lable1 = lables[i];
				BigDecimal lable2 = new BigDecimal(lable1);
				// 四舍五入保留4位小数
				bw.write(Double.toString(lable2.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue()));
				for (int j = 0; j < datas[0].length; j++) {
					double data1 = datas[i][j].value;
					BigDecimal data2 = new BigDecimal(data1);
					n = j + 1;
					// 四舍五入保留4位小数
					conten = " " + n + ":" + data2.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
					bw.write(conten);
				}
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}// class SVM