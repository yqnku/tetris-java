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
 */
public class LayerData extends Layer 
{
	private static final long serialVersionUID = 1L;
	private List<LeaderBoardsDto> leaderBoardsDtos = null;
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
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public LayerData(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
		loadDtos();
	}
	/**
	 * Paint inside Component
	 */
	@Override
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		g.drawImage(DATA_IMG, this.x+PADDING, this.y+PADDING, null);	
		this.drawShadowAndNum(g,leaderBoardsDtos);
	}
	public List<LeaderBoardsDto> loadDtos()
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		leaderBoardsDtos = new ArrayList<LeaderBoardsDto>();
		try 
		{
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			preparedStatement = connection.prepareStatement(SQL_LOAD);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				leaderBoardsDtos.add(new LeaderBoardsDto(resultSet.getString(1),Integer.parseInt(resultSet.getString(2))));
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
		return leaderBoardsDtos;
	}
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
