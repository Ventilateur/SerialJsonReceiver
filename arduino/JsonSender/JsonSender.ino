/*
 Name:		JsonSender.ino
 Created:	8/16/2016 9:40:28 AM
 Author:	Phan Vu Hoang
*/

#include "jsonFrame.h"

long dt = 0;
JsonFrame frame;

// the setup function runs once when you press reset or power the board
void setup() {
	Serial.begin(57600);
	dt = millis();
}

// the loop function runs over and over again until power down or reset
void loop() {
	frame.setId(20);
	frame.setOrientation(555, 777, 100);
	frame.setAngularSpeeds(489, 756, 912);
	frame.setFingerValues(100, 532, 498);
	Serial.println(frame.createJson());
	dt = millis();
}
