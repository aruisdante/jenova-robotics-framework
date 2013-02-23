package jenova.mappingsystem;

/**
 * Class for representing a frame, which is a grid of MapObjects. Note that the coordinates in the frame must always be positive.
 * To allow for negative coordinates, use the calcOffset function with the offsetPoint set to the desired origin.
 * EX: If a 200x200 frame is desired, but with input values ranging from -100->100 along both axis, all inputs to the frame should
 * use the calcOffset function with the CartCoord(100,100) as the offsetPoint parameter to determine the actual coordinates of that
 * point in the frame.
 * 
 * @author Adam Panzica
 *
 */
public class Frame{
	private double theta;
	private MapObject[][] points;
	private int height;
	private int width;
	private int xTrans;
	private int yTrans;
	private Frame baseFrame;
	
	/**
	 * Constructor to create a generic, uninitialized frame
	 * @param height The height of the frame
	 * @param width The width of the frame
	 */
	public Frame(int height, int width){
		this.points = new MapObject[height][width];
		this.height = height;
		this.width = width;
		this.theta = 0;
		this.xTrans = 0;
		this.yTrans = 0;
	}
	
	/**
	 * Constructor to create a frame initialized to some base MapObject
	 * @param height height of the frame
	 * @param width width of the frame
	 * @param baseObj Base MapObject to initialize all points in the frame to
	 */
	public Frame(int height, int width, MapObject baseObj){
		this.points = new MapObject[height][width];
		this.height = height;
		this.width = width;
		this.theta = 0;
		this.xTrans = 0;
		this.yTrans = 0;
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				points[y][x] = new MapObject(baseObj);
			}
		}
	}
	
	/**
	 * Constructor to create a frame based on a rotation and translation from some base frame
	 * @param height height of the frame
	 * @param width width of the frame
	 * @param baseFrame base frame to pull data out of
	 * @param theta rotation from the base frame, positive clockwise from the Y axis of the base frame
	 * @param translation CartCoord containing the X and Y translation from baseFrame of the frame
	 */
	public Frame(int height, int width, Frame baseFrame, double theta, IMapPoint translation){
		TransformCalculator transform = new TransformCalculator(theta, translation);
		CartCoord tempXY;
		
		this.height = height;
		this.width = width;
		this.points = new MapObject[height][width];
		this.theta = theta;
		this.xTrans = translation.getX();
		this.yTrans = translation.getY();
		this.baseFrame = baseFrame;
		
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				//System.out.println("Local ("+x+","+y+")");
				tempXY = transform.transformLocalToBase(new CartCoord(x,y));
				//System.out.println("Base "+tempXY);
				//System.out.println(nextPoint);
				if(baseFrame.checkBounds(tempXY)){
					setPoint(new CartCoord(x,y),baseFrame.getPoints()[tempXY.getY()][tempXY.getX()]);
				}
			}
		}
	}
	
	/**
	 * Copies the contents of the frame into a base frame
	 * @param baseFrame Frame to copy into. Note, this frame MUST be the frame from which theta, xTrans and yTrans are measured from
	 */
	public void copyToBaseFrame(){
		TransformCalculator transform = new TransformCalculator(this.theta, new CartCoord(this.xTrans,this.yTrans));
		CartCoord tempXY;
		
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				tempXY = transform.transformLocalToBase(new CartCoord(x,y));
				if(this.baseFrame.checkBounds(tempXY)){
					this.baseFrame.setPoint(tempXY,points[y][x]);
				}
			}
		}
	}
	
	/**
	 * Casts a point onto the frame
	 * @param point Coordinates of the point
	 * @param object MapObject to copy to that point
	 */
	public void castPoint(IMapPoint point, MapObject object){
		if(checkBounds(point)){
			setPoint(point, object);
		}
	}
	
	/**
	 * Casts a line between two points in the frame.
	 * @param startPoint The coordinates of the starting point of the line
	 * @param startPointObject The MapObject to place at the start of the line
	 * @param endPoint The coordinates of the ending point of the line
	 * @param endPointObject The MapObject to place at the end of the line
	 * @param lineObject A MapObject to fill all of the points between the start and end point with
	 */
	public void castLine(IMapPoint startPoint, MapObject startPointObject, IMapPoint endPoint, MapObject endPointObject, MapObject lineObject){
		PolarCoord vector = new PolarCoord(new CartCoord(endPoint.getX()-startPoint.getX(),endPoint.getY()-startPoint.getY()));
		double tempX;
		double tempY;
		double step;
		
		for(int n=0; n<=vector.getRange();n++){
			step = n/(vector.getRange());
			tempX = Math.round(vector.getRange()*step*Math.cos(vector.getTheta()))+(double)startPoint.getX();
			tempY = Math.round(vector.getRange()*step*Math.sin(vector.getTheta()))+(double)startPoint.getY();
			if(checkBounds((int)tempX,(int)tempY)){
				setPoint(new CartCoord((int)tempX,(int)tempY),lineObject);
			}
			if(checkBounds(startPoint.getX(),startPoint.getY()))  setPoint(startPoint,startPointObject);
			if(checkBounds(endPoint.getX(),endPoint.getY()))  setPoint(endPoint,endPointObject);
		}
		
	}
	
	/**
	 * Draws a rectangle in the local frame between a top left and bottom right point
	 * @param topLeft Coordinates of the top left of the rectangle
	 * @param bottomRight Coordinates of the bottom right of the rectangle
	 * @param object MapObject to fill the rectangle with
	 * @param fill True if the rectangle should be solid, false for edge only
	 */
	public void castRec(IMapPoint topLeft, IMapPoint bottomRight, MapObject object, boolean fill){
		if(fill){
			int deltaX = Math.abs(bottomRight.getX()-topLeft.getX());
			int deltaY = Math.abs(topLeft.getY()-bottomRight.getY());
			int newX;
			int newY;
			
			for(int y=0; y<deltaY; y++){
				for(int x=0; x<deltaX; x++){
					newX = x+topLeft.getX();
					newY = topLeft.getY()-y;
					if(checkBounds(newX,newY)){
						setPoint(new CartCoord(newX,newY), object);
					}
				}
			}
		}
		else{
			CartCoord topRight = new CartCoord(bottomRight.getX(),topLeft.getY());
			CartCoord bottomLeft = new CartCoord(topLeft.getX(),bottomRight.getY());
			//System.out.println("Coords: TL: "+topLeft+" TR: "+topRight+" BL: "+bottomLeft+" BR: "+bottomRight);
			castLine(topLeft, object, topRight, object, object);
			castLine(topLeft, object, bottomLeft, object, object);
			castLine(bottomRight, object, bottomLeft, object, object);
			castLine(bottomRight, object, topRight, object, object);
		}
	}
	
	/**
	 * Draws a circle (or fraction there of) in the frame from a center point. Follows the following rotation guide:
	 * Positive radius, positive angle: counter clockwise from positive x axis
	 * Positive radius, negative angle: clockwise from positive x axis
	 * Negative radius, positive angle: clockwise from negative x axis
	 * Negative radius, negative angle: counter clockwise from negative x axis
	 * @param center The coordinates of the center of the circle
	 * @param radius The radius of the circle 
	 * @param degrees The arc of the circle (E.X. 90 for quarter circle, 180 for half, 360 for full)
	 * @param object MapObject to fill the circle with
	 * @param fill True for a solid circle, false for an edge only circle
	 */
	public void castCircle(IMapPoint center, int radius, int degrees, MapObject object, boolean fill) {
		PolarCoord vector = new PolarCoord(0, (double) radius);
		CartCoord nextPoint = new CartCoord(vector.getX()+center.getX(),vector.getY() + center.getY());
		
		if(radius<0)degrees = -degrees;
		
			if (degrees > 0) {
				for(int step = 0; step-1 <= degrees; step++) {
					if (checkBounds(nextPoint)) {
						if (fill) {
							castLine(center, object, nextPoint, object, object);
						} else setPoint(nextPoint, object);
					}
					vector = new PolarCoord(step, radius);
					nextPoint = new CartCoord(vector.getX()+center.getX(), vector.getY()+center.getY());
				}
			}
			else {
				for(int step = 360; step+1 >= (360 + degrees); step--) {
					if (checkBounds(nextPoint)) {
						if (fill) {
							castLine(center, object, nextPoint, object, object);
						} else
							setPoint(nextPoint, object);
					}
					vector = new PolarCoord(step, radius);
					nextPoint = new CartCoord(vector.getX()+center.getX(), vector.getY()+center.getY());
				}
			}
	}
	
	/**
	 * @return The MapObject in the frame at (x,y)
	 */
	public MapObject[][] getPoints(){
		return this.points;
	}
	
	/**
	 * @return rotation from the base frame, positive clockwise from the Y axis of the base frame
	 */
	public double getTheta(){
		return this.theta;
	}
	
	/**
	 * @return translation along the X axis of the base frame
	 */
	public int getXTrans(){
		return this.xTrans;
	}
	
	/**
	 * @return translation along the Y axis of the base frame
	 */
	public int getYTrans(){
		return this.yTrans;
	}
	
	/**
	 * Checks to see if an X/Y coordinate falls within the bounds of the frame
	 * @param x The X value to check
	 * @param y The Y value to check
	 * @return True if (X,Y) falls within (width,height), else false
	 */
	public boolean checkBounds(int x, int y){
		return (x>=0&&x<this.width&&y>=0&&y<this.height);
	}
	
	/**
	 * Checks to see if an X/Y coordinate falls within the bounds of the frame
	 * @param point Coordinate to check if within the bounds of the frame
	 * @return True True if (X,Y) falls within (width,height), else false
	 */
	public boolean checkBounds(IMapPoint point){
		return checkBounds(point.getX(),point.getY());
	}
	
	/**
	 * Copies the data from a given MapObject into the local frame at the location specified by point. Creates a new MapObject if one
	 * does not already exist at that location, otherwise overwrites the data already at that point. NOTE: There is no bounds checking,
	 * calling function must ensure that the location is within bounds
	 * @param point Location in the frame to copy data to
	 * @param object MapObject to copy data from
	 */
	private void setPoint(IMapPoint point, MapObject object){
		if(points[point.getY()][point.getX()] == null) points[point.getY()][point.getX()] = new MapObject(object);
		else points[point.getY()][point.getX()].replace(object);
	}
	
	/**
	 * Calculates a new CartCoord based of a point and an offset point to translate the point from the offsetPoint frame into the frame 
	 * Useful for calculating locations in the frame for points that are measured from some offset point's frame,
	 * such as sensor readings from a robot located in the middle of the frame
	 * @param offsetPoint Coordinates of the offset point relative to the frame
	 * @param point Coordinates of the actual point, relative to the offset point
	 * @return Coordinates of the actual point, relative to the frame.
	 */
	public CartCoord calcOffset(IMapPoint offsetPoint, IMapPoint point){
		return new CartCoord(offsetPoint.getX()+point.getX(),offsetPoint.getY()+point.getY());
	}
	
	/**
	 * Prints out a text representation of the frame
	 */
	public String toString(){
		String framePrint = "";
		for(int y=height-1; y>=0; y--){
			for(int x=0; x<this.width; x++){
				framePrint = framePrint + points[y][x].toString();
			}
			framePrint = framePrint+"\n";
		}
		return framePrint;
	}
	
	/**
	 * @return The width of the frame
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * @return The height of the frame
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * replaces the point map in the frame with the given point map
	 * @param points point map array
	 */
	public void setPoints(MapObject[][] points) {
		this.points = points;
		this.width = points[0].length;
		this.height = points.length;
	}
}
