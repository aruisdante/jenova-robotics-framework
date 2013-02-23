package test;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*SensorManager<Integer, Double> testMan = new SensorManager<Integer, Double>();
		FirstOrderLinearization testLin1 = new FirstOrderLinearization(0, 1024, 0, 5);
		FirstOrderLinearization testLin2 = new FirstOrderLinearization(0, 1024, 0, 180);
		Potentiometer testP1 = new Potentiometer(0, "Test Pot", "Volts", testLin1);
		Potentiometer testP2 = new Potentiometer(1, "Test Pot 2", "Degrees", testLin2);
		EDUBotAnalogSensorManager testEDUMan = new EDUBotAnalogSensorManager();
		
		try {
			testEDUMan.registerSensor(0, testP1);
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
		}
		try {
			testEDUMan.registerSensor(1, testP2);
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
		}
		
		testEDUMan.printSensorList();
		
		try {
			testEDUMan.getSensorEng(0, 512);
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
		}
		try {
			testEDUMan.getSensorEng("Test Pot 2", 512);
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
		}
		try {
			testEDUMan.getSensorEng(5, 0);
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
		}
		try {
			testEDUMan.getSensorEng("Blah", 0);
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
		}
		
		testEDUMan.printSensorList();
		
		try {
			testEDUMan.getAllEng(200);
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
		}
		testEDUMan.printSensorList();
		JenovaConsole.printErrorLog();
		JenovaConsole.printStatusLog();*/
		
		/*HawkEye testHawk = new HawkEye(10, 10, 10, 10, new CartCoord(0,0),new  CartCoord(4,4), new ObjectLocationData(new CartCoord(0,0),0), new MapObject("0"));
		MapObject oneObj = new MapObject("1");
		
		
		testHawk.castLine(new CartCoord(0,0), oneObj, new CartCoord(5,5), oneObj, oneObj);		
		testHawk.updateLocation(new ObjectLocationData(new CartCoord(1,0),0));
		System.out.println(testHawk);
		testHawk.castLine(new CartCoord(0,0), oneObj, new CartCoord(5,5), oneObj, oneObj);	
		testHawk.saveToGolbal();
		System.out.println(testHawk);
		System.out.println("Tests Complete");*/
		//JenovaEDUBot testBot = new JenovaEDUBot();
		/*try {
			System.out.println(testMan.registerSensor(test1));
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println(testMan.registerSensor(test2));
		} catch (Exception e) {
			System.out.println(e);
			
		}
		try {
			testMan.registerSensor(new Potentiometer(0, "Test Pot", "Volts", null));
		} catch (Exception e) {
			System.out.println(e);
		}
		testMan.printSensorList();
		
		try {
			testMan.getSensorName("Test Pot").update(512);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			testMan.getSensorName("Test Pot 2").update(512);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		testMan.printSensorList();
		
		*/
		
		
		//MapObject oneObj = new MapObject("1");
		
		//Frame testBase = new Frame(10,10, new MapObject("0"));
		//System.out.println("Created Base Frame");
		//TransformCalculator testCalc = new TransformCalculator(Math.PI/2,new CartCoord(10,10));
		//System.out.println(testCalc);
		//System.out.println(testCalc.transformLocalToBase(new CartCoord(1,1)));
		//Frame testFrame = new Frame(10,10,testBase,(double)90*Math.PI/(double)180,new CartCoord(0,9));
		//testFrame.castRec(new CartCoord(1,5), new CartCoord(5,1), oneObj, false);
		//System.out.println("Created Test Frame");
		//testFrame.castLine(new CartCoord(1,5), oneObj, new CartCoord(5,5), oneObj, oneObj);
		
		//testFrame.castCircle(new CartCoord(4,4), -3, 90, oneObj, false);
		//System.out.println(testFrame);
		//testFrame.copyToBaseFrame();
		//System.out.println("\n\n"+testBase);
	}

}
