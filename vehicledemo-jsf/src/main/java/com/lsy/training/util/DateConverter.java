package com.lsy.training.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Date.class)
public class DateConverter implements Converter {

	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		try {
			return df.parseObject(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			context.addMessage(component.getClientId(), new FacesMessage("A major Date clusterfuck has happened!"));
		      throw new ConverterException(e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return df.format((Date)value);
	}


	
}
