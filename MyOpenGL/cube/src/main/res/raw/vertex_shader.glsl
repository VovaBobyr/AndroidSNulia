attribute vec4 a_Position;
uniform mat4 u_Matrix;
varying vec3 v_Position;

void main() {
    gl_Position = u_Matrix * a_Position;
    gl_PointSize = 10.0;
    v_Position = a_Position.xyz;
}