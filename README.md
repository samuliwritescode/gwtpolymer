# Use GWT widget as Polymer web component

So you got an existing GWT widget and you would like to publish it to pure html/JS audience preferably as a web component. In this case you have a problem and hopefully this article will help you resolve that problem.

### Extra hacks necessary to get it working. 
* GWT 2.7.0: enable Js interop compilation by adding compilation parameter jsInteropMode=JS
* Make GWT widgetset to be one file by using sso linker.

## GWT Js interop API
Traditional GWT application is a monolithic chunk of javascript binary that does it magic hidden from plain old JavaScript / html. Luckily as of version 2.7 of GWT the project has released API for next generation GWT/JS interop. https://docs.google.com/document/d/1tir74SB-ZWrs-gQ8w-lOEV3oMY6u6lF2MmNivDEihZ4/edit

By annotating classes and types that are supposed to be exposed to javascript the black box of GWT is bleached a bit. Basically you have to annotate everything that is going to be published to javascript in this manner:
 
```
@JsNamespace("$wnd.org.vaadin.webcomponents")
@JsExport
@JsType
public class WebComponentButton extends Button {
...
}
```

Note that these annotations are not transitive so you will not expose any of the methods defined in super classes or interfaces. If you refer another class then that must be exported too. With more complex widgets it is probably easier to write a wrapper class that operates the actual widget than it is to fully export the widget API and it's dependencies.

Please also note the namespace string is starting with $wnd. Without it the exported class is not visible for some reason. It is probably a bug in GWT 2.7. After exporting you can access an object inside the GWT binary in javascript by:

```
var button = new org.vaadin.webcomponents.WebComponentButton();
button.setText("hello world");
```

Another detail to consider is that widget must be added to a root panel or child of another widget or else it is going to be detached. For this an util method that does this is necessary.

```
@JsExport
public static void add(Widget w, String id) {
    RootPanel.get(id).add(w);
}
```

Note that the id here has to be unique DOM id which can be generated for example:

```
@JsExport
public static String getId() {
    return HTMLPanel.createUniqueId();
}
```

See fully working example of utils in JSUtils.java found in example project.

## Special compilation parameter

With version 2.7 this is still very much experimental so in order to enable interop the GWT compiler must be invoked with special compilation parameter called jsInteropMode that must be set to value "JS". With maven you would configure it like this in plugin section:

```
<configuration>
  <jsInteropMode>JS</jsInteropMode>
</configuration>
```

## One file widgetset.

It would be more preferable if the GWT compilation result would be just one .js file instead of multitude of them. This can be accomplished by using sso linker in widgetset.xml.

```
<collapse-all-properties />
<add-linker name="sso" />
```

## Access from JavaScript
After exporting the widget it's really easy to start using it with you web component. Full example of how to add a GWT button and click listener.

```
Polymer({
	ready: function() {
		var self = this;
	  	var button = new org.vaadin.webcomponents.WebComponentButton();
	  	button.setText(this.buttontext);
	  
	    button.addClickListener(function() {
	    	...
	  	});
	    
	  	this.$.wrappercontainer.id = org.vaadin.util.JSUtils.getId();
	  	org.vaadin.util.JSUtils.addButton(button, this.$.wrappercontainer.id);
	}
});
```