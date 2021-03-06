CdlUI
=====

Simple and easy basic UI components for android 

~~~~
The goal of this lib is to provide a very simple easy and light way to add many controls 
in one single view as easy as possible for developers.
~~~~

CdlUI is a library  that provide some basic graphic components 
   - PushButton
   - OnOffButton
   - MultiStateButton
   - Fader
   - Knobs
   
3 layouts : 
   - Free Layout
   - GridLayout
   - FlowLayout with scrollbar

And
   - Palette tool
   - Integrated progress bar
   - Integrated Custom Message Display (like Toast)

To make this lib as easy as possible for developers,  there is 
  - No XML ( except for one initial view this is all programmatically )
  - No bitmaps
  - Automatic scalable with resolution of the device 
  - Default values 
  - Default behaviriuos 
  - Very easy layouts
  - Color Schemes
  - Callbacks on events


Installation
===

- Download the library git clone :
        git://github.com/cadeli/CdlUI.git
        
- Import CdlUI project into Eclipse: 
       File -> Import Existing Projects into Workspace -> Select root directory choose CdlUI folder and Finish.

- Set CdlUI project as library into Eclipse : 
       Right click on your Android Project, choose Properties -> Android -> Library -> select isLibrary

- Add CdlUI as a reference to your project: 
        Right click on your Android Project, choose Properties -> Android -> Library -> Add and select "CdlUI" project.


How to use 
===
Layout XML file : 
----

First add  a view in your layout xml file, just as any standard view.
Exemple in activity_simple_grid_demo.xml   
  
  ```xml
  <com.cadeli.ui.CdlView
          android:id="@+id/cdlView1"
         android:layout_width="fill_parent"
         android:layout_height="0dp"
         android:layout_weight="3"
         android:background="#000000" />
   ```

Java Activity file :
---- 

In your activity in the onCreate method just after the setContentView  
      
   ```java
      protected void onCreate(Bundle savedInstanceState) {
      		super.onCreate(savedInstanceState);
      		setContentView(R.layout.activity_simple_grid_demo);
      		
      		// retrieve your view
      		CdlView mCdlView = (CdlView) findViewById(R.id.cdlView1);
		
      		//create a button
      		CdlPushButton mCdlPushButton = new CdlPushButton("My label");
		
      		// add the button to the view to the view
      		mCdlView.addCdlBaseButton(mCdlPushButton);		
		
      		//create another  button
      		CdlOnOffButton mCdlButton = new CdlOnOffButton("My Button");
		
      		// add the button to the view
      		mCdlView.addCdlBaseButton(mCdlButton);
	}
   ```
  
This lib is used in a free app on google play 
http://play.google.com/store/apps/details?id=com.cadeli.dmFree
  
Sample code at 
https://github.com/cadeli/CdlUI/tree/master/CdlUIg/src/com/cadeli/uiDemo



Demo
====

A grid of push buttons
----

With 40 lines of code

Sample code at 
https://github.com/cadeli/CdlUI/tree/master/CdlUIg/src/com/cadeli/uiDemo/AnotherGridDemo.java

  ![ScreenShot](https://raw.github.com/cadeli/CdlUI/master/CdlUIg/screenshots/scr4.png?raw=true)

Another  grid of push buttons with more style options
----

With 40 lines of code

  ![ScreenShot](https://raw.github.com/cadeli/CdlUI/master/CdlUIg/screenshots/scr1.png?raw=true)

A row of on/off buttons with scrollbar
----

With 40 lines of code. On/off buttons are two states buttons, the color of the button depends of the current state. 
A toggle is associed to the tapUp.   

  ![ScreenShot](https://raw.github.com/cadeli/CdlUI/master/CdlUIg/screenshots/scr2.png?raw=true)

An audio mixer interface with faders and knobs
----

A sample of design by this UI. State/value of each controler is available. Some interactions are available (push button to set faders value, display knobs values in button label, etc... )


  ![ScreenShot](https://raw.github.com/cadeli/CdlUI/master/CdlUIg/screenshots/scr3.png?raw=true)



Sample code at 
https://github.com/cadeli/CdlUI/tree/master/CdlUIg/src/com/cadeli/uiDemo/FakeAudioMixerActivity.java

LICENSE
====
Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/



