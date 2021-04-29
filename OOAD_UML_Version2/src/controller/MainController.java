package controller;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import dao.ShapeDao;
import model.Shape;
import view.Canvas;

/** Switch the mode via strategy pattern,
 * adding corresponding Controller to view elements base on current mode. **/
public class MainController {
	
	//create MainController of SingleObject
	private static MainController instance = new MainController();
	private Canvas canvas = Canvas.getInstance(); // view
	private ShapeDao shapeDao = ShapeDao.getInstance(); // model dao
	@SuppressWarnings("unused")
	private Mode mode;
	private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

	//make the constructor private so that this class cannot be instantiated
	private MainController() {}
	
	//Get the only object available
	public static MainController getInstance(){
		return instance;
	}
	
	public void setMode(Mode mode) {
		this.mode = mode;
		
		switch ( mode ) {
		case SELECT: // 選取 移動 群組
			canvas.setController( new SelectCanvasController()  );
			shapeDao.getAllBasicObjects()
				.forEach(o -> o.getUiObject().setListener(new SelectObjectController()));
			break;
		case ASSOCIATE: // 畫線3種
//			canvas.setState( new CreateLineState(State.ASSOCIATE.toString()) );
//			vm.setStateAllItems( new CreateLineState(State.ASSOCIATE.toString()) );
			break;
		case COMPOSITE:
//			canvas.setState( new CreateLineState(State.COMPOSITE.toString()) );
//			vm.setStateAllItems( new CreateLineState(State.COMPOSITE.toString()) );
			break;
		case GENERAL:
//			canvas.setState( new CreateLineState(State.GENERAL.toString()) );
//			vm.setStateAllItems( new CreateLineState(State.GENERAL.toString()) );
			break;
		case CLASS: // 建立2種Object
			canvas.setController( new CreateObjectController(Mode.CLASS) );
			shapeDao.getAllBasicObjects().forEach(o -> o.getUiObject().removeListener());
			break;
		case USECASE:
			canvas.setController( new CreateObjectController(Mode.USECASE) );
			shapeDao.getAllBasicObjects().forEach(o -> o.getUiObject().removeListener());
			break;
		default:
			LOGGER.warning("no such state in enum");
			break;
		}
	}

}
