/* Starter file for Project 0 */
public class Planet {

  private static final double G = 6.67e-11;

  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Planet(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {
    double dx = this.xxPos - p.xxPos; //Might need to change this order.
    double dy = this.yyPos - p.yyPos; //Might need to change this order.
    double r_squared = (dx*dx) + (dy*dy);
    double r = Math.pow(r_squared, 0.5);
    return r;
  }

  public double calcForceExertedBy(Planet p) {
    double r_squared = this.calcDistance(p)*this.calcDistance(p);
    double F = (G * this.mass * p.mass)/r_squared;
    return F;
  }

  public double calcForceExertedByX(Planet p) {
    double F = this.calcForceExertedBy(p);
    double r = this.calcDistance(p);
    double dx = p.xxPos - this.xxPos;
    double Fx =  F * dx / r;
    return Fx;
  }

  public double calcForceExertedByY(Planet p) {
    double F = this.calcForceExertedBy(p);
    double r = this.calcDistance(p);
    double dy = p.yyPos - this.yyPos;
    double Fy =  F * dy / r;
    return Fy;
  }

  public double calcNetForceExertedByX(Planet[] planets) {
    double total = 0;
    for (Planet p : planets) {
      if (this.equals(p)) {
        continue;
      }
      total += this.calcForceExertedByX(p);
    }
    return total;
  }

  public double calcNetForceExertedByY(Planet[] planets) {
    double total = 0;
    for (Planet p : planets) {
      if (this.equals(p)) {
        continue;
      }
      total += this.calcForceExertedByY(p);
    }
    return total;
  }

  public void update(double dt, double fX, double fY) {
    double ax = fX/this.mass;
    double ay = fY/this.mass;
    double vnewx = xxVel + dt * ax;
    double vnewy = yyVel + dt * ay;
    double pnewx = xxPos + dt * vnewx;
    double pnewy = yyPos + dt * vnewy;
    this.xxVel = vnewx;
    this.yyVel = vnewy;
    this.xxPos = pnewx;
    this.yyPos = pnewy;
  }

  public void draw() {
    StdDraw.picture(xxPos, yyPos, imgFileName);
  }
}
