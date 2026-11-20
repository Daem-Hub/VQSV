// The field and the local chart share the same illustrated interior and floor coordinates.
window.drawShopRoom = (context, map) => {
  const scene = assets.scenes[map.id];
  if (scene) context.drawImage(scene,0,0,map.width*map.tileSize,map.height*map.tileSize);
};
