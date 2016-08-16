// 
// 
// 

#include "jsonFrame.h"

JsonFrame::JsonFrame() {
	id = 0;
	for (int i = 0; i < DIMENSIONS; i++) {
		orientation[i] = 0;
		angularSpeeds[i] = 0;
	}
	for (int i = 0; i < NUMBER_OF_FINGERS; i++) {
		fingerValues[i] = 0;
	}
}

JsonFrame::~JsonFrame() {}

String JsonFrame::createJson() {
	String ret;
	String c = ",";
	// "{"id":id_val,"
	ret = "{\"id\":" + String(id) + c;
	// create string "[yaw,pitch,roll]"
	String ypr = "[" + String(orientation[YAW]) + c + 
							  orientation[PITCH] + c + 
							  orientation[ROLL] + "]";
	// "{"id":id_val,"ypr":[yaw,pitch,roll],"
	ret += "\"ypr\":" + ypr + c;
	// "[x,y,z]"
	String xyz = "[" + String(angularSpeeds[X]) + c +
							  angularSpeeds[Y] + c +
							  angularSpeeds[Z] + "]";
	// "{"id":id_val,"ypr":[yaw,pitch,roll],"xyz":[x,y,z],"
	ret += "\"xyz\":" + xyz + c;
	// "[index,middle,ring]"
	String imr = "[" + String(fingerValues[INDEX]) + c +
							  fingerValues[MIDDLE] + c +
							  fingerValues[RING] + "]";
	// "{"id":id_val,"ypr":[yaw,pitch,roll],"xyz":[x,y,z],"imr":[index,middle,ring]}"
	ret += "\"imr\":" + imr + "}";
	return ret;
}

void JsonFrame::getId(int & val) { val = this->id; }

void JsonFrame::getOrientation(int & yaw, int & pitch, int & roll) {
	yaw	  = this->orientation[YAW];
	pitch = this->orientation[PITCH];
	roll  = this->orientation[ROLL];
}

void JsonFrame::getAngularSpeeds(int & x, int & y, int & z) {
	x = this->angularSpeeds[X];
	y = this->angularSpeeds[Y];
	z = this->angularSpeeds[Z];
}

void JsonFrame::getFingerValues(int & index, int & middle, int & ring) {
	index  = this->fingerValues[INDEX];
	middle = this->fingerValues[MIDDLE];
	ring   = this->fingerValues[RING];
}

void JsonFrame::setId(int val) { this->id = val; }

void JsonFrame::setOrientation(int yaw, int pitch, int roll) {
	this->orientation[YAW]   = yaw;
	this->orientation[PITCH] = pitch;
	this->orientation[ROLL]  = roll;
}

void JsonFrame::setAngularSpeeds(int x, int y, int z) {
	this->angularSpeeds[X] = x;
	this->angularSpeeds[Y] = y;
	this->angularSpeeds[Z] = z;
}

void JsonFrame::setFingerValues(int index, int middle, int ring) {
	this->fingerValues[INDEX]  = index;
	this->fingerValues[MIDDLE] = middle;
	this->fingerValues[RING]   = ring;
}
