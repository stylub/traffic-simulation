export function resizeCanvas(canvas, body, riveInstance) {
  canvas.width = body.clientWidth;
  canvas.height = body.clientHeight;
  riveInstance.resizeDrawingSurfaceToCanvas();
}
