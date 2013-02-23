package jenova.mappingsystem;
/**
 * Class for calculating values for performing transforms
 * @author Adam Panzica
 */
public class TransformCalculator {
	private double sinT;
	private double cosT;
	private int xTrans;
	private int yTrans;
	
	/**
	 * Constructor for initializing a transform calculations
	 * @param theta rotation from the base frame, positive clockwise from the Y axis of the base frame
	 * @param translation Translation from the base frame to local frame
	 */
	public TransformCalculator(double theta, IMapPoint translation){
		this.sinT = Math.sin(theta);
		this.cosT = Math.cos(theta);
		this.xTrans = translation.getX();
		this.yTrans = translation.getY();
	}
	
	/**
	 * Given a local X and Y value, calculates the corresponding base frame X and Y value for a rotation
	 * @param localPoint The point in the local frame
	 * @return A CartCoord containing the X and Y value of the local point in the base frame
	 */
	public CartCoord rotateLocalToBase(IMapPoint localPoint){
		return new CartCoord((int) Math.round((this.cosT*(double)localPoint.getX()+this.sinT*(double)localPoint.getY())),
							 (int) Math.round((-1*this.sinT*localPoint.getX()+this.cosT*localPoint.getY())));
	}
	
	/**
	 * Given a base X and Y value, calculates the corresponding local frame X and Y value for a rotation
	 * @param basePoint point in the base frame
	 * @return An array containing the X and Y value of the base point in the local frame
	 */
	public CartCoord rotateBaseToLocal(IMapPoint basePoint){
		return new CartCoord((int) Math.round((this.cosT*(double)basePoint.getX()-this.sinT*(double)basePoint.getY())),
							 (int) Math.round((this.sinT*(double)basePoint.getX()+this.cosT*basePoint.getY())));
	}
	
	/**
	 * Given a local X and Y value, calculates the corresponding base frame X and Y value for a translation
	 * @param localPoint point in the local frame
	 * @return An array containing the X and Y value of the local point in the base frame
	 */
	public CartCoord translateLocalToBase(IMapPoint localPoint){
		return new CartCoord(this.xTrans+localPoint.getX(), this.yTrans+localPoint.getY());
	}
	
	/**
	 * Given a base X and Y value, calculates the corresponding local frame X and Y value for a translation
	 * @param basePoint point in the base frame
	 * @return An array containing the X and Y value of the base point in the local frame
	 */
	public CartCoord translateBaseToLocal(IMapPoint basePoint){
		return new CartCoord(basePoint.getX()-this.xTrans, basePoint.getY()-this.yTrans);
	}
	
	public String toString(){
		return "transformation matrix:\n["+cosT+" "+(-sinT)+"|"+xTrans+"]\n["+sinT+" "+cosT+"|"+yTrans+"]\n";
	}
	
	/**
	 * Given a local X and Y value, calculates the corresponding base frame X and Y value for a transformation (rotation+translation)
	 * @param localPoint Point in the local frame
	 * @return An array containing the X and Y value of the local point in the base frame
	 */
	public CartCoord transformLocalToBase (IMapPoint localPoint){
		CartCoord rotateXY = rotateLocalToBase(localPoint);
		
		return new CartCoord(rotateXY.getX()+this.xTrans, rotateXY.getY()+this.yTrans);
	}
	
	/**
	 * Given a base X and Y value, calculates the corresponding local frame X and Y value for a transformation (rotation+translation)
	 * @param basePoint Point in the base frame
	 * @return An array containing the X and Y value of the base point in the local frame
	 */
	public CartCoord transformBaseToLocal (IMapPoint basePoint){
		CartCoord rotateXY = rotateBaseToLocal(basePoint);
		CartCoord translateXY = translateBaseToLocal(basePoint);
		return new CartCoord(rotateXY.getX()-translateXY.getX(), rotateXY.getY()-translateXY.getY());
	}
}
