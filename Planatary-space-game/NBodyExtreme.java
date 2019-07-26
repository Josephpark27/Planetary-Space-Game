public class NBodyExtreme {

  public static double readRadius(String file) {
    In in = new In(file);
    int numplanets = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static PlanetExtreme[] readPlanets(String file) {
    In in = new In(file);
    int numplanets = in.readInt();
    double radius = in.readDouble();
    PlanetExtreme[] planets = new PlanetExtreme[numplanets];
    for (int i = 0; i < numplanets; i++) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String img = in.readString();
      PlanetExtreme temp_planet = new PlanetExtreme(xxPos, yyPos, xxVel, yyVel, mass, img);
      planets[i] = temp_planet;
    }
    return planets;
  }
  public static void main(String args[]) {

    /* General settings. */
    StdDraw.enableDoubleBuffering();

    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    double radius = 2.50e+11;

    /* HugDaddy settings! */
    double hug_daddy_pos = (radius/100);
    double hug_daddy_neg = (radius/100)*-1;
    double hug_daddy_x = radius * -0.9;
    double hug_daddy_y = radius * -0.9;
    String hug_daddy_image;
    String filename;
    boolean a_plus = true;
    boolean a_plus_2 = true;
    boolean a_plus_3 = true;
    boolean a_plus_4 = true;
    boolean win = false;
    boolean lose = false;

    /* Output intro images. */
    StdDraw.setScale(radius * -1, radius * 1);
    StdDraw.picture(0, 0, "Slide1.png");
    StdDraw.show();
    StdDraw.pause(100);
    StdDraw.picture(-1*(radius/20), 0, "Slide2.png");
    StdDraw.show();
    StdDraw.pause(100);
    StdDraw.picture(-1*(radius/30), 0, "Slide3.png");
    StdDraw.show();
    StdDraw.pause(100);
    StdDraw.picture(0, 0, "Slide4.png");
    StdDraw.show();
    StdDraw.pause(1000);

    /* Select HugDaddy image! */
    while (true) {
      StdDraw.picture(0, 0, "Slide4.png");
      StdDraw.show();
      StdDraw.pause(1);
      if (StdDraw.isKeyPressed(49)) {
        hug_daddy_image = "images/josh1.png";
        filename = "data/planets2.txt";
        break;
      }
      else if (StdDraw.isKeyPressed(50)) {
        hug_daddy_image = "images/josh3.png";
        filename = "data/planets2.txt";
        break;
      }
      else if (StdDraw.isKeyPressed(51)) {
        hug_daddy_image = "images/josh2.gif";
        filename = "data/planets2.txt";
        break;
      }
    }

    PlanetExtreme[] planets = readPlanets(filename);


    StdDraw.picture(0, 0, "images/starfield.jpg");
    for (int i = 0; i < planets.length; i++) {
      if (i == 3) {
        continue;
      }
      StdDraw.picture(planets[i].xxPos, planets[i].yyPos, "images/" + planets[i].imgFileName);
    }

    for (double time = 0.0; time < T; time = time + dt) {
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      for (int p = 0;p < planets.length; p++) {
        if (p == 3) {
          continue;
        }
        xForces[p] = planets[p].calcNetForceExertedByX(planets);
        yForces[p] = planets[p].calcNetForceExertedByY(planets);
      }
      for (int p = 0; p < planets.length; p++) {
        if (p == 3) {
          continue;
        }
        planets[p].update(dt, xForces[p], yForces[p]);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      StdDraw.picture(radius, radius, "images/Berk.png");
      if (a_plus) {
        StdDraw.picture(0.5*radius, 0, "images/A+.png");
      }
      if (a_plus_2) {
        StdDraw.picture(-0.5*radius, 0, "images/A+.png");
      }
      if (a_plus_3) {
        StdDraw.picture(0, 0.5*radius, "images/A+.png");
      }
      if (a_plus_4) {
        StdDraw.picture(0, -0.5*radius, "images/A+.png");
      }
      if (StdDraw.isKeyPressed(37)) {
        if ((hug_daddy_x + hug_daddy_neg) < (-1*radius)) {
          StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
        }
        else {
          hug_daddy_x += hug_daddy_neg;
          StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
        }
      }
      else if (StdDraw.isKeyPressed(39)) {
        if ((hug_daddy_x + hug_daddy_pos) > radius) {
          StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
        }
        else {
        hug_daddy_x += hug_daddy_pos;
        StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
        }
      }
      else if (StdDraw.isKeyPressed(38)) {
        if ((hug_daddy_y + hug_daddy_pos) > radius) {
          StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
        }
        else {
        hug_daddy_y += hug_daddy_pos;
        StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
        }
      }
      else if (StdDraw.isKeyPressed(40)) {
        if ((hug_daddy_y + hug_daddy_neg) < (-1*radius)) {
          StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
        }
        else {
        hug_daddy_y += hug_daddy_neg;
        StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
        }
      }
      else {
        StdDraw.picture(hug_daddy_x, hug_daddy_y, hug_daddy_image);
      }

      for (int p = 0; p < planets.length; p++) {
        if (p == 3) {
          continue;
        }
        StdDraw.picture(planets[p].xxPos, planets[p].yyPos, "images/" + planets[p].imgFileName);
      }
      StdDraw.show();
      StdDraw.pause(10);
      for (int p = 0; p < planets.length; p++) {
        if (p == 3) {
          continue;
        }
        if (hug_daddy_x > 0 && hug_daddy_y > 0 && planets[p].xxPos >0 && planets[p].yyPos >0) {
          if (Math.abs(hug_daddy_x - planets[p].xxPos) < (0.1*radius) &&
             Math.abs(hug_daddy_y - planets[p].yyPos) < (0.1*radius)) {
               lose = true;
               break;
          }
        }
        if (hug_daddy_x > 0 && hug_daddy_y < 0 && planets[p].xxPos >0 && planets[p].yyPos <0) {
          if (Math.abs(hug_daddy_x - planets[p].xxPos) < (0.1*radius) &&
              Math.abs(hug_daddy_y - planets[p].yyPos) < (0.1*radius)) {
               lose = true;
               break;
          }
        }
        if (hug_daddy_x < 0 && hug_daddy_y > 0 && planets[p].xxPos <0 && planets[p].yyPos >0) {
          if (Math.abs(hug_daddy_x - planets[p].xxPos) < (0.1*radius) &&
              Math.abs(hug_daddy_y - planets[p].yyPos) < (0.1*radius)) {
               lose = true;
               break;
          }
        }
        if (hug_daddy_x < 0 && hug_daddy_y < 0 && planets[p].xxPos <0 && planets[p].yyPos <0) {
          if (Math.abs(hug_daddy_x - planets[p].xxPos) < (0.1*radius) &&
              Math.abs(hug_daddy_y - planets[p].yyPos) < (0.1*radius)) {
               lose = true;
               break;
          }
        }
      }
      if (a_plus) {
          if (Math.abs(hug_daddy_x - 0.5*radius) < (0.1*radius) &&
              Math.abs(hug_daddy_y) < (0.1*radius)) {
                a_plus = false;
          }
        // if (hug_daddy_x == 0.5*radius && hug_daddy_y == 0) {
        //   a_plus = false;
        // }
      }
      if (a_plus_2) {
        if (Math.abs(hug_daddy_x + 0.5*radius) < (0.1*radius) &&
            Math.abs(hug_daddy_y) < (0.1*radius)) {
              a_plus_2 = false;
        }
        // if (hug_daddy_x == (-0.5*radius) && hug_daddy_y == 0) {
        //   a_plus_2 = false;
        // }
      }
      if (a_plus_3) {
        if (Math.abs(hug_daddy_x) < (0.1*radius) &&
            Math.abs(hug_daddy_y - 0.5*radius) < (0.1*radius)) {
              a_plus_3 = false;
        }
        // if (hug_daddy_x == 0 && hug_daddy_y == (0.5*radius)) {
        //   a_plus_3 = false;
        // }
      }
      if (a_plus_4) {
        if (Math.abs(hug_daddy_x) < (0.1*radius) &&
            Math.abs(hug_daddy_y + 0.5*radius) < (0.1*radius)) {
              a_plus_4 = false;
        }
        // if (hug_daddy_x == 0 && hug_daddy_y == (-0.5*radius)) {
        //   a_plus_4 = false;
        // }
      }
      if (hug_daddy_x == radius && hug_daddy_y == radius && !a_plus && !a_plus_2 && !a_plus_3 && !a_plus_4) {
        win = true;
        break;
      }
      else if (lose) {
        break;
      }
    }
    if (win) {
      StdDraw.picture(0, 0, "images/Win.png");
      StdDraw.show();
      StdDraw.pause(4000);
    }
    else {
      StdDraw.picture(0, 0, "images/Lose.png");
      StdDraw.show();
      StdDraw.pause(4000);
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
