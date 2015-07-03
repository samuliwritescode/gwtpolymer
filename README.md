# Use GWT widget as Polymer web component

So you got an existing GWT widget and you would like to publish it to pure html/JS audience preferably as a web component. In this case you have a problem and hopefully this article will help you with that problem.

Hacks necessary to get it working
* Annotate selected classes and types to javascript with GWT Js interop. 
* GWT 2.7.0: enable Js interop compilation by adding compilation parameter jsInteropMode=JS
* Make GWT widgetset to be one file by using sso linker.

## GWT Js interop API
Traditional GWT application is a monolithic chunk of javascript binary that does it magic hidden from plain old Javascript / html. Luckily as of version 2.7 of GWT the project has released API for next generation GWT/JS interop. https://docs.google.com/document/d/1tir74SB-ZWrs-gQ8w-lOEV3oMY6u6lF2MmNivDEihZ4/edit 

By annotating classes and types that are supposed to be exposed to javascript the black box of GWT is bleached.
 
'''
@JsNamespace("$wnd.org.vaadin.webcomponents")
@JsExport
@JsType
public class WebComponentButton extends Button {
...
}
'''

With version 2.7 this is still very much experimental so in order to enable interop the GWT compiler must be invoked with special compilation parameter called jsInteropMode that must be set to value "JS". With maven you would configure it like this in plugin section:

'''
<configuration>
  <jsInteropMode>JS</jsInteropMode>
</configuration>
'''

## Access from JavaScript

'''
var button = new org.vaadin.webcomponents.WebComponentButton();
button.setText(this.buttontext);
'''