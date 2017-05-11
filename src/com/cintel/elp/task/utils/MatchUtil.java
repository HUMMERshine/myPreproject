package com.cintel.elp.task.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.cintel.elp.entity.basicfun.Preproject;


public class MatchUtil {
	private String [] preId;
	private double[][] matrix;
	private double[] target;
	private double[] max;
	private double[] min;
	private double[] weight;

	public Map<String, Double> earthQuake(List<Preproject> sample, Preproject object) {
		matrix = new double[sample.size()][5];
		preId = new String[sample.size()];
		int row = 0;
		for (Preproject preproject : sample) {
			preId[row] = preproject.getPre_id();
			matrix[row][0] = preproject.getPreCondition().getDisaster_strength();
			matrix[row][1] = invertTime(preproject.getPre_time().toString());
			matrix[row][2] = preproject.getPreCondition().getDisaster_people()
					/ preproject.getPreCondition().getDisaster_area();
			matrix[row][3] = invertClimate(preproject.getPreCondition().getClimate());
			matrix[row][4] = invertGeography(preproject.getPreCondition().getGeography());
			row++;
		}

		target = new double[5];
		target[0] = object.getPreCondition().getDisaster_strength();
		target[1] = invertTime(object.getPre_time().toString());
		target[2] = object.getPreCondition().getDisaster_people() / object.getPreCondition().getDisaster_area();
		target[3] = invertClimate(object.getPreCondition().getClimate());
		target[4] = invertGeography(object.getPreCondition().getGeography());

		return getResult();
	}

	public Map<String, Double> getResult() {
		Map<String, Double> map = new HashMap<>();
		
		getMaxMin();
		
		for (int i = 0; i < matrix.length; i++) {
			guiyihua(matrix[i]);
		}
		guiyihua(target);
		
		getWeight();
		

		for (int i = 0; i < matrix.length; i++) {
			double sum = 0;
			for (int j = 0; j < matrix[0].length; j++) {
				sum += (matrix[i][j] - target[j]) * (matrix[i][j] - target[j]) * weight[j];
			}
			
			map.put(preId[i], Math.sqrt(sum));
		}
		
		for(int i = 0; i < matrix.length; i++){
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println(Arrays.toString(target));
		System.out.println(Arrays.toString(weight));
		
		System.out.println(map);
		
		
		return map;
	}

	public void getMaxMin() {
		max = new double[matrix.length];
		min = new double[matrix.length];
		
		for (int i = 0; i < matrix[0].length; i++) {
			double temp_max = Integer.MIN_VALUE;
			double temp_min = Integer.MAX_VALUE;

			for (int j = 0; j < matrix.length; j++) {
				if (matrix[j][i] > temp_max) {
					temp_max = matrix[j][i];
				}
				if (matrix[j][i] < temp_min) {
					temp_min = matrix[j][i];
				}
			}
			max[i] = temp_max;
			min[i] = temp_min;
		}
	}

	public void guiyihua(double[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] <= min[i]) {
				array[i] = 0;
			} else if (array[i] >= max[i]) {
				array[i] = 1;
			} else {
				array[i] = (array[i] - min[i]) / (max[i] - min[i]);
			}
		}
	}

	public void getWeight() {
		weight = new double[matrix[0].length];
		int len = matrix.length;
		double[] avg = new double[matrix[0].length];
		double[] value = new double[matrix[0].length];
		double all = 0;

		for (int j = 0; j < matrix[0].length; j++) {
			double sum = 0;
			for (int i = 0; i < len; i++) {
				sum += matrix[i][j];
			}
			avg[j] = sum / len;
		}

		for (int j = 0; j < matrix[0].length; j++) {
			double sum = 0;
			for (int i = 0; i < len; i++) {
				sum += (matrix[i][j] - avg[j]) * (matrix[i][j] - avg[j]);
			}
			value[j] = Math.sqrt((sum / len));
			all += value[j];
		}

		for (int i = 0; i < matrix[0].length; i++) {
			weight[i] = value[i] / all;
		}
	}

	public int invertTime(String time) {
		String string = time.split("\\s")[1].split(":")[0];
		int t = Integer.parseInt(string);
		return (t > 8 && t < 18) ? 1 : 2;
	}

	public double invertClimate(String climate) {
		int res = 1;
		if (climate.indexOf("雨") != -1) {
			return 3;
		}
		if (climate.indexOf("雪") != -1) {
			return 4;
		}
		if (climate.indexOf("干旱") != -1) {
			return 2;
		}
		return 1;
	}

	public double invertGeography(String geography) {
		int res = 1;
		if (geography.indexOf("平原") != -1) {
			return 2;
		}
		if (geography.indexOf("高原") != -1) {
			return 3;
		}
		if (geography.indexOf("沿海") != -1) {
			return 4;
		}
		return 1;
	}

}
