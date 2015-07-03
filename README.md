# Use GWT widget as Polymer web component

Hacks necessary to get it working
* Annotate selected classes and types to javascript with GWT Js interop. 
* GWT 2.7.0: enable Js interop compilation by adding compilation parameter jsInteropMode=JS
* Make GWT widgetset to be one file by using sso linker.