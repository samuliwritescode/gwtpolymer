package org.vaadin.webcomponents.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.js.JsExport;
import com.google.gwt.core.client.js.JsNamespace;
import com.google.gwt.core.client.js.JsType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

@JsNamespace("$wnd.org.vaadin.webcomponents")
@JsExport
@JsType
public class WebComponentButton extends Button {
    
    public void addClickListener(final JavaScriptObject js) {
        addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                JSUtils.runNative(js);
            }
        });
    }

    public WebComponentButton() {
        super("");
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }
}
