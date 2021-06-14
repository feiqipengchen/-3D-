<template>
  <div id="result">
    <h4>效果展示{{ zhongLei }}{{size}}</h4>
    <div id="canvas"></div>
  </div>
</template>

<script>
//import * as Three from 'three';
import * as Three from 'three/build/three.module.js';
import PubSub from 'pubsub-js'
import {MTLLoader, OBJLoader} from 'three-obj-mtl-loader';
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls.js'

export default {
  data() {
    return {
      publicPath: process.env.BASE_URL,
      //three中的数据
      camera: null,
      scene: null,
      renderer: null,
      mesh: null,
      object: null,
      texture: null,
      controls: null,
      container: null,
      ambientLight: null,//衣服颜色
      //传递数据
      message: [1, 2, 3],
      //材质转换
      mtlLoader: null,
      objloader: null,
      //选择衣服号
      yiFu: 1,
      //选择衣服材质
      caiZhi: 1,
      //修改衣服种类
      zhongLei:'shangyi',
      //修改衣服大小
      size:'L',
      mouseX: 0,
      mouseY: 0,
      windowHalfX:0,
      windowHalfY: 0,
    }
  },
  mounted() {
//改变衣服颜色
    PubSub.subscribe('updateMessage', (msg, index) => {
      this.updateMessage(index)
    })
    //改变衣服模型
    PubSub.subscribe('updateYiFu', (msg, index) => {
      this.updateYiFu(index)
    })
    //改变衣服材质
    PubSub.subscribe('updateCaiZhi', (msg, index) => {
      this.updateCaiZhi(index)
    })
    //改变衣服种类
    PubSub.subscribe('updateZhongLei', (msg, index) => {
      this.updateZhongLei(index)
    })
    //改变衣服大小
    PubSub.subscribe('updateSize', (msg, index) => {
      this.updateSize(index)
    })
    this.init()
  },
  methods: {
    // 初始化
    init() {
      this.createContainer()
      this.windowHalfX=250
      this.windowHalfY=250
      this.createScene() // 创建场景
      this.createMesh() // 创建网格模型
      this.createCamera() // 创建相机
      this.createLight() // 创建光源
      this.createRender() // 创建渲染器
      this.createControls() // 创建控件对象
      // document.addEventListener('mousemove', this.onDocumentMouseMove, false);
      window.addEventListener('resize', this.onWindowResize, false);
      this.render() // 渲染

    },
    createContainer() {
      this.container = document.getElementById('canvas');
      this.container.className = "canvas-box";
    },
    // 创建场景
    createScene() {
      this.scene = new Three.Scene();
      //添加中心坐标：
      this.scene.add(new Three.AxisHelper(500));
    },
    // 创建网格模型
    /* createMesh(){
       let that = this;

  // manager
  var object;
       function loadModel() {

         object.traverse( function ( child ) {

           if ( child.isMesh ) child.material.map = texture;

         } );

         object.position.y = - 95;
         that.scene.add( object );

       }

       var manager = new Three.LoadingManager( loadModel );

       manager.onProgress = function ( item, loaded, total ) {

         console.log( item, loaded, total );

       };

       // texture

       var textureLoader = new Three.TextureLoader( manager );

       var texture = textureLoader.load( 'static/model/newModel1/10.jpg' );

       // model

       function onProgress( xhr ) {

         if ( xhr.lengthComputable ) {

           var percentComplete = xhr.loaded / xhr.total * 100;
           console.log( 'model ' + Math.round( percentComplete, 2 ) + '% downloaded' );

         }

       }

       function onError() {}

       var loader = new OBJLoader( manager );

       loader.load( 'static/model/newModel1/71.obj', function ( obj ) {

         object = obj;

       }, onProgress, onError );
      },*/
    createMesh() {
      let that = this;
      new OBJLoader().setPath('/static/model/').load(that.zhongLei+'/'+that.yiFu + '.obj', function (obj) {
        var texture = new Three.TextureLoader().load('/static/caizhi/' + that.caiZhi + '.jpg');
        // 颜色贴图中已经包含了光照信息，所以直接使用不受光照影响的基础网格材质MeshBasicMaterial
        obj.children[0].material = new Three.MeshBasicMaterial({
          map: texture,//设置颜色纹理贴图
        })
        obj.position.x = 0;
        obj.position.y = 0;
        obj.position.z = 0;
        that.scene.remove(that.mesh)
        that.mesh = obj
        that.scene.add(that.mesh)
        console.log('mesh', obj)

      })
    },
    // 创建相机
    createCamera() {
      this.container.className = "canvas-box";
      let k = this.container.clientWidth / this.container.clientHeight
      this.camera = new Three.OrthographicCamera(-300 * k, 300 * k, 300, -300, -300, 1000);


      ///////////////
      /* // 相机随着模型的大小初始化位置
       var boxHelper = new Three.BoxHelper()
       //this.camera = new Three.PerspectiveCamera(70, container.clientWidth/container.clientHeight, 1, 10000);
       boxHelper.setFromObject(this.scene)
       var center = boxHelper.geometry.boundingSphere.center
       var radius = boxHelper.geometry.boundingSphere.radius
       var cameraPos = new Three.Vector3(center.x+this.mesh.position.x,radius * .8 +center.y+this.mesh.position.y,radius * 1.5 + center.z + this.mesh.position.z)
       var lookPos = new Three.Vector3(center.x+this.mesh.position.x,center.y+this.mesh.position.y,center.z+this.mesh.position.z);
       this.camera.position.copy(cameraPos)*/


      this.camera.position.set(200, 100, 100);
      this.camera.lookAt(this.scene.position)
      this.scene.add(this.camera);
    },
    // 创建光源
    createLight() {
      this.ambientLight = new Three.AmbientLight(0xD8BFD8, 0.4);
      this.scene.add(this.ambientLight);
      let pointLight0 = new Three.PointLight(0xffffff, 0.8);
      pointLight0.position.set(400, 200, 300); //点光源位置
      this.scene.add(pointLight0);
      let pointLight1 = new Three.PointLight(0xffffff, 0.8);
      pointLight1.position.set(-400, -200, -300); //点光源位置
      this.scene.add(pointLight1);
      let pointLight2 = new Three.PointLight(0xffffff, 0.8);
      pointLight2.position.set(0, 0, 0); //点光源位置
      this.scene.add(pointLight2);

    },
    // 创建渲染器
    createRender() {
      this.renderer = new Three.WebGLRenderer();
      this.renderer.setPixelRatio(window.devicePixelRatio);
      this.renderer.setClearColor(0x00FFFF, 1);//背景色	(灰色gray)

      this.renderer.setSize(400, 400);
      this.container.appendChild(this.renderer.domElement);

    },
    //渲染
    render() {
      requestAnimationFrame(this.render)
      this.camera.position.x += ( this.mouseX - this.camera.position.x ) * .05;
      this.camera.position.y += ( - this.mouseY - this.camera.position.y ) * .05;

      this.camera.lookAt( this.scene.position );
      this.renderer.render(this.scene, this.camera)

      //this.mesh.rotateY(0.01)
    },
    // 创建控件对象
    createControls() {
      this.controls = new OrbitControls(this.camera, this.renderer.domElement)
    },
    //改变衣服颜色
    updateMessage(index) {
      this.message = index
      const temp = "rgb(" + this.message[0] + "," + this.message[1] + "," + this.message[2] + ")"
      this.mesh.children[0].material.color = new Three.Color(temp)
    },
    //重新加载衣服模型
    updateYiFu(index) {
      this.yiFu = index
      this.createMesh()
    },
    //重新加载衣服材质
    updateCaiZhi(index) {
      this.caiZhi = index
      this.createMesh()
    },
    //重新加载衣服种类
    updateZhongLei(index) {
      this.zhongLei = index
      this.createMesh()
    },
    //重新加载衣服大小
    updateSize(index) {
      this.size = index
    },
    onWindowResize() {
      console.log("1")
      this.windowHalfX =250;
      this.windowHalfY =  250;

      this.camera.aspect = window.innerWidth / window.innerHeight;
      this.camera.updateProjectionMatrix();

      this.renderer.setSize(400, 400 );

    },
   /* onDocumentMouseMove(event) {
      console.log("2")
      this.mouseX = (event.clientX - this.windowHalfX) / 2;
      this.mouseY = (event.clientY - this.windowHalfY) / 2;
    }*/
  }
}
</script>

<style scoped>
#result {
}

#canvas {
  border: 0.1px solid red;
  position: absolute;
  width: 400px;
  height: 400px;
}
</style>
