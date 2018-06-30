package sem3.game.util;

public class Time { // Time class of various functions

	public long time0, time1;

	public boolean timerStart = false;

	public Time() {

	}

	public void startTimer() { // Starting a timer
		if (!timerStart) {
			timerStart = true;
			time0 = System.nanoTime();
		}
	}

	public double getCurrentTime() { // Getting the current time in seconds from when the timer was started
		if (timerStart) {
			time1 = System.nanoTime();
			double deltaTime = (double) (time1 - time0) / 1_000_000_000;
			return deltaTime;
		} else {
			return 0.0;
		}
	}

	public void stopTimer() { // Stopping the timer
		timerStart = false;
	}

	public boolean wait(double miliSeconds) { // Waiting certain time to execute a particular instruction, return true if we have to wait and false if not
		startTimer();
		if (getCurrentTime() > miliSeconds) {
			stopTimer();
			return false;
		} else {
			return true;
		}
	}

}
