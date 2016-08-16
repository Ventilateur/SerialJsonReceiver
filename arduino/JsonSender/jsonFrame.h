// jsonFrame.h

#ifndef _JSONFRAME_h
#define _JSONFRAME_h

#if defined(ARDUINO) && ARDUINO >= 100
	#include "arduino.h"
#else
	#include "WProgram.h"
#endif

#define DIMENSIONS			3
#define NUMBER_OF_FINGERS	3

class JsonFrame {
public:
	enum Orientation { YAW, PITCH, ROLL };
	enum AngularSpeeds { X, Y, Z };
	enum FingerValues { INDEX, MIDDLE, RING };

	JsonFrame();
	~JsonFrame();
	String createJson();

	void getId(int& val);
	void getOrientation(int& yaw, int& pitch, int& roll);
	void getAngularSpeeds(int& x, int& y, int& z);
	void getFingerValues(int& index, int& middle, int& ring);

	void setId(int val);
	void setOrientation(int yaw, int pitch, int roll);
	void setAngularSpeeds(int x, int y, int z);
	void setFingerValues(int index, int middle, int ring);

	template <typename T> void sendFrame(T target) {
		target.write(createJson());
		target.flush();
	}
private:
	int id;
	int orientation[DIMENSIONS];
	int angularSpeeds[DIMENSIONS];
	int fingerValues[NUMBER_OF_FINGERS];
};

#endif

