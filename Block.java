public class Block
{
	// Used by all blocks.
	private String texture;
	private String color;
	private int damage;
	private int id;
	private int turns;

	// Used by player block.
	private int hp;
	private int xLocation;
	private int yLocation;

	// Used with colors.
	private static final String RESET = "\u001B[0m";
	

	public Block(String code, String tex, int i, int dam, int t)
	{
		hp = 10;

		color = code;
		texture = code + tex + RESET;
		damage = dam;
		id = i;

		turns = t;

	}

	// Methods that get or change block attributes.

	public String getTexture()
	{
		return texture;
	}

	public void setLocation(int x, int y)
	{
		xLocation = x;
		yLocation = y;
	}

	public int getX()
	{
		return xLocation;
	}

	public int getY()
	{
		return yLocation;
	}

	public int getHp()
	{
		return hp;
	}

	public void takeDamage(int x)
	{
		hp += x;
	}

	public void checkStatus()
	{
		if (hp <= 0)
		{
			System.out.println("You died!");
			System.exit(0);
		}
		else if (turns <= 0)
		{
			System.out.println("You're out of turns!");
			System.exit(0);
		}
		else
		{

		}
	}

	public void showStatus()
	{
		if (turns >= 1000)
		{
			turns -= 1000;
			System.out.println("You win!");
			System.out.println("\u001B[32m" + "[ HP: " + getHp() + " ]" + RESET);
			System.out.println("[ Turns: " + turns + " ]");
			System.exit(0);
		}
		else
		{
			System.out.println("\u001B[32m" + "[ HP: " + getHp() + " ]" + RESET);
			System.out.println("[ Turns: " + turns + " ]");
		}
	}

	public int getDamage()
	{
		return damage;
	}

	public int getId()
	{
		return id;
	}

	public void changeTurns(int t)
	{
		turns += t;
	}

	public int getTurn()
	{
		return turns;
	}



}