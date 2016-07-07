package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import cn.quicy.tetris.dto.LeaderBoardsDto;
/**
 * DataBase
 * @author quicy
 * @version 1.0
 */
public class LayerData extends Layer 
{
	private static final long serialVersionUID = 1L;
	private List<LeaderBoardsDto> globalLeaderBoardsDtos = null;
	//TODO My SQL
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/tetrisGame";
	private static final String DB_USER = "tetris";
	private static final String DB_PWD = "quicy";
	private static final String SQL_LOAD = "select name,score from user_score where type_id = 1 order by score desc limit 4;";
	static
	{
		try 
		{
			Class.forName(DRIVER);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Global Leader Boards
	 */
	private final Image DATA_IMG = new ImageIcon("graphics/string/db.png").getImage();
	/**
	 * Constructor
	 * @param m_x start-position-x
	 * @param m_y start-position-y
	 * @param m_w width
	 * @param m_h height
	 */
	public LayerData(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
		loadDtos();
	}
	/**
	 * Paint inside Component
	 * @param g Graphics
	 */
	@Override
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		g.drawImage(DATA_IMG, this.x+PADDING, this.y+PADDING, null);	
		this.drawShadowAndNum(g,globalLeaderBoardsDtos);
	}
	public List<LeaderBoardsDto> loadDtos()
	{
		//连接
		Connection connection = null;
		//准备
		PreparedStatement preparedStatement = null;
		//结果
		ResultSet resultSet = null;
		globalLeaderBoardsDtos = new ArrayList<LeaderBoardsDto>();
		try 
		{
			//连接到数据库
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			//载入SQL命令
			preparedStatement = connection.prepareStatement(SQL_LOAD);
			//运行得到结果---迭代器
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				//从1开始标号
				globalLeaderBoardsDtos.add(new LeaderBoardsDto(resultSet.getString(1),Integer.parseInt(resultSet.getString(2))));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (connection != null) 
				{
					connection.close();
				}
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}
				if(resultSet != null)
				{
					resultSet.close();
				}
			} catch (Exception e2) {
			}
		}
		return globalLeaderBoardsDtos;
	}
	//TODO 数据库插入
	public void SetDataDto(List<LeaderBoardsDto> leaderBoardsDtos)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try 
		{
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);	
			//TODO 数据库访问
			preparedStatement = connection.prepareStatement(SQL_LOAD);
			preparedStatement.execute();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if (connection != null) 
				{
					connection.close();
				}
				if(preparedStatement != null)
				{
					preparedStatement.close();
				}
			} catch (Exception e2) {
			}
		}
	}
}
