webpackJsonp([0],{EKxH:function(t,e){},K0iw:function(t,e){},"Xlg/":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=i("ifoU"),a=i.n(s),n={render:function(){this.$createElement;this._self._c;return this._m(0)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"wait-content"},[e("div",{staticClass:"content_choose zero"},[e("div",{staticClass:"loading"},[e("span"),this._v(" "),e("span"),this._v(" "),e("span"),this._v(" "),e("span"),this._v(" "),e("span"),this._v(" "),e("span")])])])}]};var c=i("VU/8")({name:"LoadWait",data:function(){return{}}},n,!1,function(t){i("EKxH")},null,null).exports,l=i("ka46"),o=i("rqVl"),r={name:"Pic",data:function(){return{}},props:{picture:{type:Object},createDialog:{type:Function}},methods:{like:function(t){var e=t;this.picture.collected==t?(this.picture.collected=0,e=0):this.picture.collected=t;var i="/api/b64picture/setCollected?id="+this.picture.id+"&rank="+e;this.axios({method:"get",url:i}).then(function(t){}),this.setIconRed()},setIconRed:function(){for(var t=document.getElementById("like"+this.picture.id).children,e=this.picture.collected,i=0;i<t.length;i++)t[i].style.color=i<e?"red":""},showDetails:function(){var t="details"+this.picture.id,e=document.getElementById(t);"inline-block"==e.style.display?e.style.display="none":e.style.display="inline-block"},editRemark:function(){var t=document.getElementById("remark"+this.picture.id),e=document.getElementById("edit"+this.picture.id);t.style.display="none",e.style.display="block",e.focus()},setRemark:function(){var t=this;this.axios({method:"post",url:"/api/b64picture/setRemark",data:{id:t.picture.id,remark:t.picture.remark}}).then(function(e){"false"==e.data&&t.$message.warning("后台系统错误")})}},mounted:function(){"null"!=this.picture.remark&&""!=this.picture.remark||(this.picture.remark="说点什么吧。"),this.setIconRed();var t=this;document.getElementById("outer1"+t.picture.id).onmouseleave=function(){document.getElementById("details"+t.picture.id).style.display="none"};var e=document.getElementById("remark"+this.picture.id),i=document.getElementById("edit"+this.picture.id);i.onmouseleave=function(){e.style.display="block",i.style.display="none",t.setRemark()}}},d={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"outer1",attrs:{id:"outer1"+t.picture.id}},[i("div",{staticClass:"img-wraper1"},[i("span",{on:{click:function(e){return t.createDialog(t.picture)}}},[i("img",{attrs:{src:t.picture.b64,alt:""}})]),t._v(" "),i("div",{staticClass:"dots-wrapper"},[i("ul",{staticClass:"dots clearfix",on:{click:t.showDetails}},[i("li"),t._v(" "),i("li"),t._v(" "),i("li")])])]),t._v(" "),i("div",{staticClass:"detials1",attrs:{id:"details"+t.picture.id}},[i("div",{staticClass:"info1"},[i("h2",{staticClass:"title1"},[t._v("\n                 "+t._s(t.picture.categy)+"\n             ")]),t._v(" "),i("h3",{staticClass:"category1"},[i("i",{staticClass:"fas fa-clock"}),t._v("\n                 "+t._s(t.picture.time.split("T")[0])+"\n             ")]),t._v(" "),i("p",{staticClass:"briefintro1",attrs:{id:"remark"+t.picture.id},on:{click:t.editRemark}},[t._v("\n                 "+t._s(t.picture.remark)+"\n             ")]),t._v(" "),i("textarea",{directives:[{name:"model",rawName:"v-model",value:t.picture.remark,expression:"picture.remark"}],staticClass:"briefintro1 remarkInput",attrs:{id:"edit"+t.picture.id},domProps:{value:t.picture.remark},on:{input:function(e){e.target.composing||t.$set(t.picture,"remark",e.target.value)}}})]),t._v(" "),i("div",{staticClass:"star-warper1"},[i("ul",{staticClass:"star1",attrs:{id:"like"+t.picture.id}},[i("li",{staticClass:"fas fa-heart",on:{click:function(e){return t.like(1)}}}),t._v(" "),i("li",{staticClass:"fas fa-heart",on:{click:function(e){return t.like(2)}}}),t._v(" "),i("li",{staticClass:"fas fa-heart",on:{click:function(e){return t.like(3)}}}),t._v(" "),i("li",{staticClass:"fas fa-heart",on:{click:function(e){return t.like(4)}}})]),t._v(" "),i("span",{staticClass:"fab fa-weibo"})])])])},staticRenderFns:[]};var u=i("VU/8")(r,d,!1,function(t){i("nXK8")},"data-v-2ed2a47b",null).exports,h={name:"PictureList",components:{LoadWait:c,OnePictureShow:l.default,AddPic:o.a,Pic:u},data:function(){return{username:this.$route.params.username,categy:this.$route.params.categy,ispublic:this.$route.params.ispublic,deletelist:[],pictureList:[],ids:[],finished:!1,show_Pic:!1,picExample:null,openAdd:!1,status:!0,deleteMap:{},downStatus:!1,showList:!1}},methods:{getPic:function(){var t=this.$route.params.username,e=this.$route.params.categy,i=this.$route.params.ispublic,s=this,n="/api/b64pictures?username="+t+"&categy="+e+"&ispublic="+i;s.axios({method:"get",url:n}).then(function(e){if(s.ids=e.data,n="/api/b64picture?username="+t+"&id=",0==e.data.length&&(s.finished=!0),s.deleteMap=new a.a,0==s.GLOBAL.pictureList.length&&(s.GLOBAL.pictureList=new a.a,s.GLOBAL.deleteMap=new a.a),s.showList)for(var i=0;i<s.GLOBAL.deleteList.length;i++){var c=s.GLOBAL.deleteList[i];s.getPicById(n,c,!0),i==s.GLOBAL.deleteList.length-1&&(s.finished=!0)}else for(var l=0;l<s.ids.length;l++){var o=s.ids[l];s.getPicById(n,o,!0),l==s.ids.length-1&&(s.finished=!0)}})},globalSearch:function(){var t=this.$route.params.username,e=this.$route.params.categy,i=(this.$route.params.ispublic,this);i.axios({method:"post",url:"/api/b64pictures/classified",data:{username:t,categy:e}}).then(function(e){i.ids=e.data;var s="/api/b64picture?username="+t+"&id=";0==e.data.length&&(i.finished=!0),i.deleteMap=new a.a,0==i.GLOBAL.pictureList.length&&(i.GLOBAL.pictureList=new a.a,i.GLOBAL.deleteMap=new a.a);for(var n=0;n<i.ids.length;n++){var c=i.ids[n];i.getPicById(s,c,!0),n==i.ids.length-1&&(i.finished=!0)}})},getPicById:function(t,e,i){var s=this;if(s.GLOBAL.pictureList.has(e))return s.pictureList.push(s.GLOBAL.pictureList.get(e)),void s.deleteMap.set(e,s.GLOBAL.deleteMap.get(e));var a=t+e;s.axios({method:"get",url:a}).then(function(t){s.pictureList.push(t.data),s.GLOBAL.pictureList.set(e,t.data),s.GLOBAL.deleteMap.set(e,"delete"),s.deleteMap.set(e,"delete")})},changeBtn:function(t,e){for(var i=document.getElementsByClassName("btn1"),s=0;s<i.length;s++)i[s].style.display=t;i=document.getElementsByClassName("btn2");for(var a=0;a<i.length;a++)i[a].style.display=e},showFloatText:function(t){for(var e=document.getElementsByClassName("top"),i=0;i<e.length;i++)e[i].style.display=t},deleteStatus1:function(){this.quitDelete(),this.status=!0,this.GLOBAL.deleteStatus=!0,this.changeBtn("none","inline")},changeIcon:function(t){if(this.deleteMap.has(t)){var e=document.getElementById("pic"+t);"/static/icon/delete.png"==e.getAttribute("src")?e.setAttribute("src","/static/icon/confirm.png"):e.setAttribute("src","/static/icon/delete.png")}"delete"==this.GLOBAL.deleteMap.get(t)?(this.GLOBAL.deleteMap.set(t,"confirm"),this.deleteMap.has(t)&&this.deleteMap.set(t,"confirm")):(this.GLOBAL.deleteMap.set(t,"delete"),this.deleteMap.has(t)&&this.deleteMap.set(t,"delete"))},quitDelete:function(){this.GLOBAL.deleteStatus=!1,this.status=!1;for(var t=0;t<this.GLOBAL.deleteList.length;t++)this.changeIcon(this.GLOBAL.deleteList[t]);this.changeBtn("inline","none"),this.GLOBAL.deleteList=[]},deletePic:function(t){var e=this.GLOBAL.deleteList.indexOf(t);e<0?(this.GLOBAL.deleteList.push(t),this.changeIcon(t)):(this.GLOBAL.deleteList.splice(e,e+1),this.changeIcon(t))},comDelete:function(){for(var t=this,e=0;e<this.GLOBAL.deleteList.length;e++)this.changeIcon(this.GLOBAL.deleteList[e]);this.status=!1,this.GLOBAL.status=!0,this.changeBtn("inline","none"),this.axios({method:"post",data:this.GLOBAL.deleteList,url:"/api/b64pictures/delete"}).then(function(e){t.$message.success("成功删除"+e.data+"张图片"),t.GLOBAL.deleteStatus=!1,t.$router.push({name:"PictureListWait",params:{username:t.username,categy:t.categy,ispublic:t.ispublic}})}),this.GLOBAL.deleteList=[]},faceRecog:function(){this.$message.info("请选择一张图片"),"人物"!=this.categy&&this.ispublic?this.$message.warning("当前分类无法聚合"):(this.changeBtn("none","none"),document.getElementById("face1").style.display="none",document.getElementById("face2").style.display="inline",document.getElementById("face3").style.display="inline",this.quitDelete(),this.GLOBAL.deleteStatus=!0,this.status=!0)},comRecog:function(t){this.status=!1,this.GLOBAL.status=!1,document.getElementById("face1").style.display="inline",document.getElementById("face2").style.display="none",document.getElementById("face3").style.display="none",this.changeBtn("inline","none");for(var e=0;e<this.GLOBAL.deleteList.length;e++)this.changeIcon(this.GLOBAL.deleteList[e]);if("人物"!=this.categy&&this.ispublic)this.$message.warning("选中的照片不属于人物照。");else if(1==t)if(1!=this.GLOBAL.deleteList.length)this.$message.warning("所选图片图片的数量不正确");else{var i=this,s="/api/face/collections?id="+i.GLOBAL.deleteList[0]+"&ispublic="+i.GLOBAL.pripassword;this.axios({method:"get",url:s}).then(function(t){if(0==t.data.length)i.$message.warning("聚合失败");else{i.ids=t.data,i.pictureList=[];for(var e="/api/b64picture?username="+i.username+"&id=",s=0;s<i.ids.length;s++){var a=i.ids[s];i.getPicById(e,a,!1)}i.$message.success("聚合成功")}})}this.GLOBAL.deleteList=[]},downLoadPics:function(){this.downStatus=!1,this.GLOBAL.downStatus=!1;for(var t=this.GLOBAL.deleteList,e=0;e<t.length;e++)this.downLoadPic(this.GLOBAL.pictureList.get(t[e]));this.quitDelete()},downLoadPic:function(t){var e=document.createElement("a"),i=new MouseEvent("click");e.download=t.picname,e.href=t.b64,e.dispatchEvent(i)},quitDown:function(t){2!=t?(this.downStatus=!1,this.GLOBAL.downStatus=!1,this.quitDelete()):this.$router.push({name:"PictureListWait",params:{username:this.GLOBAL.username,categy:this.GLOBAL.categy,ispublic:this.GLOBAL.ispublic}})},createDialog:function(t){this.show_Pic=!0,this.picExample=t},closeDialog:function(t){this.show_Pic=t},addPic:function(){this.openAdd=!0},closeAddDialog:function(t){this.openAdd=!1}},created:function(){this.GLOBAL.showList&&(this.GLOBAL.showList=!1,this.showList=!0),this.GLOBAL.globalSearch?(console.log(this.GLOBAL.globalSearch),this.GLOBAL.globalSearch=!1,this.globalSearch()):this.getPic()},mounted:function(){this.GLOBAL.deleteStatus&&this.changeBtn("none","inline"),this.status=this.GLOBAL.deleteStatus,this.deleteMap=this.GLOBAL.deleteMap,this.downStatus=this.GLOBAL.downStatus}},p={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[t.downStatus||t.showList?t._e():i("div",{staticClass:" div-height"},[i("Button",{staticClass:"btn success text1 btn1",attrs:{icon:"ios-cloud-upload-outline",id:"deletebtn"},on:{click:t.deleteStatus1}},[t._v("删除图片")]),t._v(" "),i("Button",{staticClass:"btn success btn2 text2",attrs:{icon:"ios-cloud-upload-outline"},on:{click:t.quitDelete}},[t._v("取消删除")]),t._v(" "),i("Button",{staticClass:"btn success text1 btn2",attrs:{icon:"ios-cloud-upload-outline"},on:{click:t.comDelete}},[t._v("确认删除")]),t._v(" "),t.ispublic&&"人物"!=t.categy?t._e():i("Button",{staticClass:"face btn success",attrs:{icon:"ios-cloud-upload-outline",id:"face1"},on:{click:t.faceRecog}},[t._v("人脸聚合")]),t._v(" "),i("Button",{staticClass:"face btn success",attrs:{icon:"ios-cloud-upload-outline",id:"face3"},on:{click:function(e){return t.comRecog(0)}}},[t._v("取  消")]),t._v(" "),i("Button",{staticClass:"face btn success",attrs:{icon:"ios-cloud-upload-outline",id:"face2"},on:{click:function(e){return t.comRecog(1)}}},[t._v("确认聚合")])],1),t._v(" "),i("div",{staticClass:"piclists clearfix"},[i("div",{staticClass:"piclist list1 clearfix"},[i("ul",{staticClass:"myul1"},t._l(t.pictureList,function(e,s){return i("div",{key:s},[s%4==0?i("li",{staticClass:"div"},[t.status?i("div",{staticClass:"top"},[i("div",{staticClass:"text"},[t._v("\n                  "+t._s(e.picname)+"\n                ")]),t._v(" "),i("img",{staticClass:"del",attrs:{src:"/static/icon/"+t.deleteMap.get(e.id)+".png",id:"pic"+e.id},on:{click:function(i){return t.deletePic(e.id)}}})]):t._e(),t._v(" "),i("Pic",{attrs:{picture:e,createDialog:t.createDialog}})],1):t._e()])}),0)]),t._v(" "),i("div",{staticClass:"piclist list2 clearfix"},[i("ul",{staticClass:"myul1"},t._l(t.pictureList,function(e,s){return i("div",{key:s},[s%4==1?i("li",{staticClass:"div"},[t.status?i("div",{staticClass:"top"},[i("div",{staticClass:"text"},[t._v("\n                  "+t._s(e.picname)+"\n                ")]),t._v(" "),i("img",{staticClass:"del",attrs:{src:"/static/icon/"+t.deleteMap.get(e.id)+".png",id:"pic"+e.id},on:{click:function(i){return t.deletePic(e.id)}}})]):t._e(),t._v(" "),i("Pic",{attrs:{picture:e,createDialog:t.createDialog}})],1):t._e()])}),0)]),t._v(" "),i("div",{staticClass:"piclist list3 clearfix"},[i("ul",{staticClass:"myul1"},t._l(t.pictureList,function(e,s){return i("div",{key:s},[s%4==2?i("li",{staticClass:"div"},[t.status?i("div",{staticClass:"top"},[i("div",{staticClass:"text"},[t._v("\n                  "+t._s(e.picname)+"\n                ")]),t._v(" "),i("img",{staticClass:"del",attrs:{src:"/static/icon/"+t.deleteMap.get(e.id)+".png",id:"pic"+e.id},on:{click:function(i){return t.deletePic(e.id)}}})]):t._e(),t._v(" "),i("Pic",{attrs:{picture:e,createDialog:t.createDialog}})],1):t._e()])}),0)]),t._v(" "),i("div",{staticClass:"piclist list4 clearfix"},[i("ul",{staticClass:"myul1"},t._l(t.pictureList,function(e,s){return i("div",{key:s},[s%4==3?i("li",{staticClass:"div"},[t.status?i("div",{staticClass:"top"},[i("div",{staticClass:"text"},[t._v("\n                  "+t._s(e.picname)+"\n                ")]),t._v(" "),i("img",{staticClass:"del",attrs:{src:"/static/icon/"+t.deleteMap.get(e.id)+".png",id:"pic"+e.id},on:{click:function(i){return t.deletePic(e.id)}}})]):t._e(),t._v(" "),i("Pic",{attrs:{picture:e,createDialog:t.createDialog}})],1):t._e()])}),0)])]),t._v(" "),t.show_Pic||t.showList?t._e():i("div",[t.downStatus?t._e():i("div",{staticClass:"add-icon-wrapper",on:{click:t.addPic}},[i("i",{staticClass:"iconfont add-icon"},[t._v("")])]),t._v(" "),t.downStatus?i("div",{staticClass:"add-icon-wrapper1",on:{click:t.downLoadPics}},[i("i",{staticClass:"iconfont add-icon1"},[t._v("")])]):t._e(),t._v(" "),t.downStatus?i("div",{staticClass:"add-icon-wrapper2",on:{click:function(e){return t.quitDown()}}},[i("i",{staticClass:"iconfont add-icon2"},[t._v("")])]):t._e()]),t._v(" "),t.showList?i("div",{staticClass:"add-icon-wrapper2",on:{click:function(e){return t.quitDown(2)}}},[i("i",{staticClass:"iconfont add-icon2"},[t._v("")])]):t._e(),t._v(" "),t.show_Pic?i("OnePictureShow",{attrs:{picture:t.picExample},on:{closeDialog:t.closeDialog,downLoad:t.downLoadPic}}):t._e(),t._v(" "),t.openAdd?i("AddPic",{attrs:{username:t.username,categy:t.categy,ispublic:t.ispublic},on:{closeDialog:t.closeAddDialog}}):t._e()],1)},staticRenderFns:[]};var L=i("VU/8")(h,p,!1,function(t){i("K0iw")},"data-v-39dec714",null);e.default=L.exports},nXK8:function(t,e){}});
//# sourceMappingURL=0.c2b32132921006f3ec8d.js.map