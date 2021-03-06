
package com.cintel.elp.common.utils.echarts.json;


import java.lang.reflect.Type;

import com.cintel.elp.common.utils.echarts.code.SeriesType;
import com.cintel.elp.common.utils.echarts.series.Bar;
import com.cintel.elp.common.utils.echarts.series.Chord;
import com.cintel.elp.common.utils.echarts.series.Force;
import com.cintel.elp.common.utils.echarts.series.Funnel;
import com.cintel.elp.common.utils.echarts.series.Gauge;
import com.cintel.elp.common.utils.echarts.series.Island;
import com.cintel.elp.common.utils.echarts.series.K;
import com.cintel.elp.common.utils.echarts.series.Line;
import com.cintel.elp.common.utils.echarts.series.Map;
import com.cintel.elp.common.utils.echarts.series.Pie;
import com.cintel.elp.common.utils.echarts.series.Radar;
import com.cintel.elp.common.utils.echarts.series.Scatter;
import com.cintel.elp.common.utils.echarts.series.Series;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


public class SeriesDeserializer implements JsonDeserializer<Series> {
    
    /**
     * 设置json,typeOfT,context值
     *
     * @param json
     * @param typeOfT
     * @param context
     */
    public Series deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        String _type = jsonObject.get("type").getAsString();
        SeriesType type = SeriesType.valueOf(_type);
        Series series = null;
        switch (type) {
            case line:
                series = context.deserialize(jsonObject, Line.class);
                break;
            case bar:
                series = context.deserialize(jsonObject, Bar.class);
                break;
            case scatter:
                series = context.deserialize(jsonObject, Scatter.class);
                break;
            case funnel:
                series = context.deserialize(jsonObject, Funnel.class);
                break;
            case pie:
                series = context.deserialize(jsonObject, Pie.class);
                break;
            case force:
                series = context.deserialize(jsonObject, Force.class);
                break;
            case gauge:
                series = context.deserialize(jsonObject, Gauge.class);
                break;
            case map:
                series = context.deserialize(jsonObject, Map.class);
                break;
            case island:
                series = context.deserialize(jsonObject, Island.class);
                break;
            case k:
                series = context.deserialize(jsonObject, K.class);
                break;
            case radar:
                series = context.deserialize(jsonObject, Radar.class);
                break;
            case chord:
                series = context.deserialize(jsonObject, Chord.class);
                break;
        }
        return series;
    }
}
