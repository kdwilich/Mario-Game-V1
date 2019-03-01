
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

class CoinBlock extends Sprite
{
  static BufferedImage cb_unhit = null;
  static BufferedImage cb_hit = null;

  static Random rand = new Random();

  //coin block constructor
  CoinBlock(Model m, int _x, int _y)
  {
    super(m);
    x = _x;
    y = _y - 110;
    w = 75;
    h = 75;

    if(cb_unhit == null)
    {
      try
      {
      	cb_unhit = ImageIO.read(new File("./images/blockUnhit.png"));
      	cb_hit = ImageIO.read(new File("./images/blockHit.png"));
      }
      catch(Exception e)
      {
      	e.printStackTrace(System.err);
      	System.exit(1);
      }
    }
  }

  //coin block json constructor
  CoinBlock(Json ob, Model m)
  {
    super(m);
    type = ob.getString("type");
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		num_hits = (int)ob.getDouble("num_hits");

    if(cb_unhit == null)
    {
      try
      {
      	cb_unhit = ImageIO.read(new File("./images/blockUnhit.png"));
      	cb_hit = ImageIO.read(new File("./images/blockHit.png"));
      }
      catch(Exception e)
      {
      	e.printStackTrace(System.err);
      	System.exit(1);
      }
    }
  }


  boolean update()
  {
    return true;
  }

  void draw(Graphics g)
  {
    if(num_hits < 5)
      g.drawImage(cb_unhit, x - model.scrollPos, y, w, h, null);
    else
      g.drawImage(cb_hit, x - model.scrollPos, y, w, h, null);
  }

  Json marshall()
  {
    Json ob = Json.newObject();
		ob.add("type", "CoinBlock");
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h",h);
		ob.add("num_hits", num_hits);

		return ob;
  }

  boolean am_i_a_coin_block()
  {
    return true;
  }

  void pop_out(Model m, int _x, int _y)
  {
    horiz_vel = rand.nextDouble() * 16 - 8;
    vert_vel = -20.0;


    Coin c = new Coin(m, _x, _y, vert_vel, horiz_vel);
    model.sprites.add(c);
  }


}
