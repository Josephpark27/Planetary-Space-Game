public class NBody {

  public static double readRadius(String file) {
    In in = new In(file);
    int numplanets = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String file) {
    In in = new In(file);
    int numplanets = in.readInt();
    double radius = in.readDouble();
    Planet[] planets = new Planet[numplanets];
    for (int i = 0; i < numplanets; i++) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String img = in.readString();
      Planet temp_planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
      planets[i] = temp_planet;
    }
    return planets;
  }
  public static void main(String args[]) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    StdDraw.setScale(radius * -1, radius);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    for (int i = 0; i < planets.length; i++) {
      StdDraw.picture(planets[i].xxPos, planets[i].yyPos, "images/" + planets[i].imgFileName);
    }
    StdDraw.enableDoubleBuffering();
    for (double time = 0.0; time < T; time = time + dt) {
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];
      for (int p = 0;p < planets.length; p++) {
        xForces[p] = planets[p].calcNetForceExertedByX(planets);
        yForces[p] = planets[p].calcNetForceExertedByY(planets);
      }
      for (int p = 0;p < planets.length; p++) {
        planets[p].update(dt, xForces[p], yForces[p]);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for (int p = 0; p < planets.length; p++) {
        StdDraw.picture(planets[p].xxPos, planets[p].yyPos, "images/" + planets[p].imgFileName);
      }
      StdDraw.show();
      StdDraw.pause(10);
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
    StdAudio.close();
  }
}
