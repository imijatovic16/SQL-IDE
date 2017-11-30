package app;

import java.awt.BorderLayout;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import controller.ActionsContainer;
import files.InfFileFactory;
import interpreters.StringUtil;
import model.Storage;
import users.UserManager;
import view.AboutDialog;
import view.ASUFDialog;
import view.DataBaseConnection;
import view.InfHorizontalSplitPane;
import view.InfMenu;
import view.InfToolBar;
import view.JsonViewerDialog;
import view.LogIn;
import view.RightPanel;
import view.adminPanel.PromoteUserTabPanel;

public class MainFrame extends JFrame {

	private JsonViewerDialog j;
	private static MainFrame instance = null;
	private InfHorizontalSplitPane infHorizontalSplitPane;
	private ActionsContainer actionsContainer;
	private InfToolBar infToolBar;
	private AboutDialog aboutFrame;
	private LogIn login;
	private PromoteUserTabPanel promoteUser;
	private UserManager userManager;
	private ImageIcon icon;
	private File storageFile;
	private Storage storage;
	private InfFileFactory infFileFactory;
	private RightPanel currentRightPanel;
	private DataBaseConnection dataBaseConnection;
	private DatabaseMetaData dataBaseMetaData;
	private Connection conn;
	private ASUFDialog addOrSortDialog;

	private MainFrame() {
		storageFile = FileSystemView.getFileSystemView().getHomeDirectory();
		storage = new Storage();
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.initialize();

		}

		return instance;

	}

	public void initialize() {
		j = new JsonViewerDialog();
		infHorizontalSplitPane = new InfHorizontalSplitPane();
		actionsContainer = new ActionsContainer();
		infToolBar = new InfToolBar();
		aboutFrame = new AboutDialog();
		login = new LogIn();
		userManager = new UserManager();
		userManager.start();
		login = new LogIn();
		infFileFactory = new InfFileFactory();
		dataBaseConnection = new DataBaseConnection();
		addOrSortDialog = new ASUFDialog();
	}

	public void addingGuestComponents() {
		organizeGuestFrame();
		setJMenuBar(new InfMenu());
		getContentPane().add(infHorizontalSplitPane, BorderLayout.CENTER);
		// add(infToolBar, BorderLayout.NORTH);
	}

	public void addingAdminComponents() {

	}

	public void organizeGuestFrame() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout());
		setSize(700, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("InfViewer");
		icon = new ImageIcon(getClass().getResource("/images/small/privilegijum.png"));
		setIconImage(icon.getImage());
	}

	public void guest() {
		addingGuestComponents();
		setVisible(true);
		getLogin().setVisible(false);
	}

	public void user() {
		addingGuestComponents();// obrisi kad radis za konkretno
		setVisible(true);// obrisi kad radis za konkretno
		getLogin().setVisible(false);// obrisi kad radis za konkretno
	}

	public void admin() {
		addingGuestComponents();// obrisi kad radis za konkretno
		setVisible(true);// obrisi kad radis za konkretno
		getLogin().setVisible(false);// obrisi kad radis za konkretno
	}

	public void headAdmin() {
		addingGuestComponents();// obrisi kad radis za konkretno
		setVisible(true);// obrisi kad radis za konkretno
		getLogin().setVisible(false);// obrisi kad radis za konkretno
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public JsonViewerDialog getJ() {
		return j;
	}

	public ActionsContainer getActionsContainer() {
		return actionsContainer;
	}

	public AboutDialog getAboutFrame() {
		return aboutFrame;
	}

	public void setDataBaseMetaData(DatabaseMetaData dataBaseMetaData) {
		this.dataBaseMetaData = dataBaseMetaData;
		// String[] types = {"TABLE"};
		// try {
		//// ResultSet rs = dataBaseMetaData.getTables(null, null, null, types);
		//// while(rs.next()){
		//// System.out.println(rs.getString("TABLE_NAME"));
		//// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void setAddOrSortDialog(ASUFDialog addOrSortDialog) {
		this.addOrSortDialog = addOrSortDialog;
	}

	public DataBaseConnection getDataBaseConnection() {
		return dataBaseConnection;
	}

	public InfHorizontalSplitPane getInfHorizontalSplitPane() {
		return infHorizontalSplitPane;
	}

	public InfToolBar getInfToolBar() {
		return infToolBar;
	}

	public LogIn getLogin() {
		return login;
	}

	public PromoteUserTabPanel getPromoteUser() {
		return promoteUser;
	}

	public void setStorageFile(File storageFile) {
		this.storageFile = storageFile;
	}

	public File getStorageFile() {
		return storageFile;
	}

	public ASUFDialog getAddOrSortDialog() {
		return addOrSortDialog;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
		getInfHorizontalSplitPane().getLeftScrollPane().getInfTree().newRoot(storage);
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public InfFileFactory getInfFileFactory() {
		return infFileFactory;
	}

	public void setCurrentRightPanel(RightPanel currentRightPanel) {
		this.currentRightPanel = currentRightPanel;
	}

	public RightPanel getCurrentRightPanel() {
		return currentRightPanel;
	}

}
