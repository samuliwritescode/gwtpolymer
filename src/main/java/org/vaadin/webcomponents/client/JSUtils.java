package org.vaadin.webcomponents.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.js.JsExport;
import com.google.gwt.core.client.js.JsNamespace;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

//Necessary utility functions so that JS and GWT plays nicely together.
@JsNamespace("$wnd.org.vaadin.util")
public class JSUtils {

    // Adds object into DOM based on id.
    @JsExport
    public static void addWidget(Widget w, String jsid) {
        RootPanel.get(jsid).add(w);
    }

    // Create unique id
    @JsExport
    public static String getId() {
        return HTMLPanel.createUniqueId();
    }

    // Treat parameter as JS function and run it.
    public native static void runNative(JavaScriptObject js) /*-{
                                                             js();
                                                             }-*/;
}
