package com.lsy.training.indexing;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

/**
 * Created with IntelliJ IDEA.
 * User: marcus
 * Date: 05.12.13
 * Time: 06:32
 * To change this template use File | Settings | File Templates.
 */
public class EngineTypeFieldBridge implements FieldBridge {


    final EngineTypeBridge engineTypeBridge = new EngineTypeBridge();

    @Override
    public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
        Field field  = new Field(name+".de"
                                , engineTypeBridge.objectToString(value)
                                , luceneOptions.getStore()
                                , luceneOptions.getIndex()
                                , luceneOptions.getTermVector());
        field.setBoost(luceneOptions.getBoost());
        document.add(field);

        Field field2  = new Field(name+".en"
                , value.toString()
                , luceneOptions.getStore()
                , luceneOptions.getIndex()
                , luceneOptions.getTermVector());
        field2.setBoost(luceneOptions.getBoost());
        document.add(field2);

    }
}
