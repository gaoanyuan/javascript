/*部分浏览器没有console对象，或者部分浏览器支持console,但是如果没有按F12打开控制台
 则console对象也不存在，这时对于引用了console对象的代码就会抛出NullPoint异常*/
function ConsoleFnc(){
    function init(){
        if(console){
            Console.prototype = console.constructor.prototype;
        }else{
            //当浏览器没有console对象时，自身提供空实现，防止NullPoint.
            this.log = function(){}
            this.debug = function(){}
            this.warn = function(){}
            this.error = function(){}
            this.info = function(){}
        }
        window.Console = new ConsoleFnc();
    }
    init();
}
Console();

(function(){
    function GlobalObject(){

    }
    function PageObject(){

    }
    function LogicObject(){

    }
    this.globalObject = GlobalObject();
    this.pageObject = PageObject();
    this.logicObject = LogicObject();
    LogicObject.prototype = pageObject;
    PageObject.prototype = globalObject;
    function IntellijLoad(){
        //客户调用的核心API，所有页面的逻辑代码被封存在fnc中
        this.use = function(modules,fnc){
            var obj = resolveModule(modules);
            fnc(obj);
        }
        //预加载模块，为后面的调用提前准备.
        this.preLoad = function(modules){

        }

        //解决模块之间的依赖.
        function resolveModule(modules){

        }
        //添加新的模块
        function add(options){

        }
        //预先加载jquery的核心dom操作.
        add({
            name:"jquery",
            path:"/js/jquery-1.8.3.js",
            async:false
        });
    }
})();
