webpackJsonp([1],{PZVj:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s={name:"AddCategy",data:function(){return{imgcategy:{username:"",categy:"",remark:""}}},props:{username:{type:String}},methods:{closeDialog:function(){console.log("关闭"),this.$emit("closeDialog",!1)},onSubmit:function(){this.imgcategy.username=this.username;var t=this;this.axios({method:"post",url:"/api/categy/create",data:t.imgcategy}).then(function(e){e.data?(t.$message.success("添加成功"),t.closeDialog()):t.$message.warning("创建失败，该分类已经存在!")})}}},i={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"categydialog"}},[a("div",{staticClass:"categyborder"},[a("div",{staticClass:"closeImg",on:{click:t.closeDialog}}),t._v(" "),a("div",{staticClass:"form-wrapper"},[a("div",{staticClass:"categy-title"},[t._v("创建新相册")]),t._v(" "),a("ul",{staticClass:"categyform",attrs:{model:t.imgcategy}},[a("li",{staticClass:"categy-label"},[t._v("相册名称")]),t._v(" "),a("li",[a("el-input",{staticClass:"categy-input",attrs:{placeholder:"请输入分类名称"},model:{value:t.imgcategy.categy,callback:function(e){t.$set(t.imgcategy,"categy",e)},expression:"imgcategy.categy"}})],1),t._v(" "),a("li",{staticClass:"categy-label"},[t._v("相册描述")]),t._v(" "),a("li",[a("el-input",{staticClass:"categy-input",attrs:{type:"textarea",placeholder:"相册的简单描述...(300字以内。)",autosize:{minRows:6,maxRows:6}},model:{value:t.imgcategy.remark,callback:function(e){t.$set(t.imgcategy,"remark",e)},expression:"imgcategy.remark"}})],1)]),t._v(" "),a("div",{staticClass:"categy-btn"},[a("el-button",{staticClass:"button1",attrs:{type:"primary"},on:{click:t.onSubmit}},[a("span",{staticClass:"iconfont"},[t._v("")]),t._v(" 创建")])],1)])])])},staticRenderFns:[]};var c={name:"SubMain2",components:{AddCategy:a("VU/8")(s,i,!1,function(t){a("WsPZ")},null,null).exports},data:function(){return{username:this.$route.params.username,categy:this.$route.params.categy,showDialog:!1}},methods:{toPictureList:function(t,e,a){this.$router.push({name:"PictureListWait",params:{username:t,categy:e,ispublic:a}})},createDialog:function(){this.showDialog=!0},closeDialog:function(t){this.showDialog=!1;var e=this,a="/api/categy?username="+this.GLOBAL.username+"&ispublic=false";this.axios({method:"get",url:a}).then(function(t){e.categy=t.data,e.GLOBAL.pricategy=t.data})}},created:function(){this.GLOBAL.ispublic=this.GLOBAL.pripassword}},n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("ul",{staticClass:"myul"},[a("li",{on:{click:t.createDialog}},[t._m(0)]),t._v(" "),t._l(t.categy,function(e){return a("li",[a("span",{on:{click:function(a){return t.toPictureList(t.username,e,!1)}}},[a("i",{staticClass:"iconfont"},[t._v("")]),t._v(" "),a("span",{attrs:{id:"font"}},[t._v(t._s(e))])])])})],2),t._v(" "),t.showDialog?a("AddCategy",{attrs:{username:t.username},on:{closeDialog:t.closeDialog}}):t._e()],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("i",{staticClass:"iconfont"},[this._v("")]),this._v(" "),e("span",{attrs:{id:"font"}},[this._v("新建")])])}]};var o=a("VU/8")(c,n,!1,function(t){a("e+Ut")},"data-v-f388d384",null);e.default=o.exports},WsPZ:function(t,e){},"e+Ut":function(t,e){}});
//# sourceMappingURL=1.53902ad2e3bb962d2473.js.map