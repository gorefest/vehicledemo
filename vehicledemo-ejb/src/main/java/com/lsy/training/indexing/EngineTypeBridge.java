package com.lsy.training.indexing;

import com.lsy.training.model.Engine;
import org.hibernate.search.bridge.StringBridge;
import org.hibernate.search.bridge.TwoWayFieldBridge;

/**
 * Created with IntelliJ IDEA.
 * User: marcus
 * Date: 05.12.13
 * Time: 06:27
 * To change this template use File | Settings | File Templates.
 */
public class EngineTypeBridge implements StringBridge {
    @Override
    public String objectToString(Object object) {
        String result;
        switch ((Engine.EngineType) object) {
            case PETROL:
                result="Benzin";
                break;
            case DIESEL:
                result="Diesel";
                break;
            case PETROL_HYBRID:
                result="Bezin-Hybrid";
                break;
            case DIESEL_HYBRID:
                result="Diesel-Hybrid";
                break;
            case LPG:
                result="Autogas";
                break;
            case CNG:
                result="Erdgas";
                break;
            case OTHER:
            default:
                result="andere";
                break;
        }
        return result;
    }
}
