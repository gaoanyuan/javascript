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
ConsoleFnc();
/**
 * JS的HashMap只支持key为string,这个是对其的补充，可以用Object为key
 * @constructor
 */
function HashMap() {
    this.array = [];
    this.push = function (key, value) {
        for (var index in this.array) {
            if (this.array.hasOwnProperty(index)) {
                if (key === this.array[index].key) {
                    this.array[index].value = value;
                    return;
                }
            }
        }
        this.array.push({
            key: key,
            value: value
        });
    };
    this.get = function (key) {
        for (var index in this.array) {
            if (this.array.hasOwnProperty(index)) {
                if (key === this.array[index].key) {
                    return this.array[index].value;
                }
            }
        }
        return null;
    }
}
/**
 * js 没有set，此类扩展了array，内部元素不会重复.注意暂时对于元素是否相等的判断来自于 === (不同于==，前者不会进行类型转换)。
 * @constructor
 */
function Set() {
    var array = Array.apply(null, arguments);
    for (var i = 0, l = array.length; i < l; i++) {
        this[i] = array[i];
    }
    this.length = array.length;
}
Set.prototype = new Array();
Set.prototype.push = function (obj) {
    var flag = false;
    for (var name in this) {
        if (this.hasOwnProperty(name)) {
            if (this[name] === obj) {
                flag = true;
                break;
            }
        }
    }
    if (!flag)
        Array.prototype.push.apply(this, [obj]);
};
/**
 * 因为浏览器版本和实现的混乱，此函数是唯一可以判断变量类型的函数  "Array", "Object", "Number", "String" , "Boolean", "Function"
 * eg:typeIs(modules,typeIs.String);
 */
function typeIS(obj, type) {
    var typeClass = Object.prototype.toString.call(obj).slice(8, -1);
    return obj !== undefined && obj !== null && typeClass === type;
}
typeIS.Array = "Array";
typeIS.Object = "Object";
typeIS.Number = "Number";
typeIS.String = "String";
typeIS.Boolean = "Boolean";
typeIS.Function = "Function";
typeIS.Undefined = "undefined"
function INT(){
    if(!window.intellijLoad){
        window.intellijLoad = new IntellijLoad();
    }
    return window.intellijLoad;
}
/**
 * This module need Jquery module.
 */
function BROWSERTYPE(){
    var IE = "msie";
    var FIREFOX = "mozilla";
    var CHROME = "chrome";
    var SAFARI = "safari";
    BROWSERTYPE.isIE  = function(){
        return $.browser[IE] || false;
    }
    BROWSERTYPE.isChrome = function(){
        return $.browser[CHROME] || false;
    }
    BROWSERTYPE.isSafari = function(){
        return $.browser[SAFARI] || false;
    }
    BROWSERTYPE.isFireFox = function(){
        return $.browser[FIREFOX] || false;
    }
}


    function GlobalObject(){}
    function PageObject(){}
    function LogicObject(){}

    this.globalObject = GlobalObject();
    this.pageObject = PageObject();
    this.logicObject = LogicObject();
    LogicObject.prototype = pageObject;
    PageObject.prototype = globalObject;

    function IntellijLoad(){
        this.modules = undefined;
        this.basePath = "/" + window.location.pathname.split("/")[1];
        this.baseCssUrl = this.basePath;
        this.baseJsUrl =  this.basePath;
        var loadReadyEvent = "LOAD_READY";
        this.addEvent = function(name, options, func){

        }
        this.fireEvent = function(name, options){

        }

        function PageDomain(){

        }
        PageDomain.prototype = this;

        /**
         * 解决模块之间的依赖.
         * @param module
         */
        function loadModule(moduleName){
            if(!loadJS[moduleName]){
                var requiredModules = this.modules[moduleName].required;
                if(requiredModules){
                    for(var index in requiredModules){
                        loadModule(requiredModules[index]);
                    }
                }
                loadCSS(moduleName);
                loadJS(moduleName);
            }
        }
        /**
         * 添加新的模块，模块分为异步同步2中，对于jquery这种没有使用闭包的，必须使用同步，用来确保多个模块之间的加载顺序
         * 每个模块可能包括js文件和css文件(模块也会有依赖的模块，所以依赖的模块会被优先加载)
         * @param options
         */
        this.addModule = function(name, func){
            if(this.modules[name] === undefined){
                this.modules[name] = {};
            }
            this.modules[name].func = func;
            this.fireEvent(loadReadyEvent, name);
        }

        /**
         * 模块加载调用的子模块，用来加载JS文件
         * @param options
         */
        function loadJS(moduleName){
            var module = this.modules[moduleName];
            var headEle = document.getElementsByTagName("head")[0];
            var script = document.createElement("script");
            if(module.js.substring(0,1) === "/"){
                script.src = module.js;
            }else{
                script.src = this.baseJsUrl + module.js;
            }
            script.type = "text/javascript";
            if(module.async){
                script.async = "async";
            }else{
                script["onload"] = function(){
                    this.fireEvent(loadReadyEvent,moduleName);
                }
            }
            headEle.appendChild(script);
        }
        /**
         * 模块加载使用的子模块，用来加载CSS文件
         * @param options
         */
        function loadCSS(moduleName){

        }

        //客户调用的核心API，所有页面的逻辑代码被封存在fnc中
        this.use = function(modules,fnc){
            var needModules = [];
            if(typeIS(modules,typeIS.String)){
                needModules.add(modules);
            }else if(typeIS(modules, typeIS.Array)){
                needModules=modules;
            }else{
                Console.error("Need modules error");
                return;
            }
            for(var index in needModules){
                loadModule(needModules[index]);
            }
        }
        this.register = function(options){
            if(this.modules === undefined){
                this.modules = options.modules;
            }else{
                for(var moduleName in options.modules){
                    this.modules[moduleName] = options.modules[moduleName]
                }
            }
            if(options.baseJsUrl){
                this.baseJsUrl = this.basePath + options.baseJsUrl;
            }
            if(options.baseCssUrl){
                this.baseCssUrl = this.basePath + options.baseCssUrl;
            }
        }
    }

    function YUI(options){
        if(window.intellijLoad){
            window.intellijLoad = new IntellijLoad();
        }
        if(options){
            window.intellijLoad.register(options)
        }
        return window.intellijLoad;
    }
    YUI({
        baseJsUrl:"/js",
        modules:{
            "jQuery":{
                js:"jquery-1.8.3.js",
                async:false
            }
        }
    });


