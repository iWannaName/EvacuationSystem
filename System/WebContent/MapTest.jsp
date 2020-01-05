<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html {width: 100%;height: 100%; margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=18KFfk5BGw6gHtfuvV8pSg08wgc65xFc"></script>
	<title>疏散路线</title>
</head>
<body>
	<div id="container" style="width:600px;height:100%"></div>
    <div id="panel" style="position:absolute;left:620px;top:5px"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("container");
	map.centerAndZoom(new BMap.Point(121.62,38.92), 12);
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	 var transit = new BMap.DrivingRoute(map, {
		renderOptions: {
			map: map,
			panel: "panel",
			enableDragging : true //起终点可进行拖拽
		},  
	});
	transit.search("大连大学","大连火车站");
	var pt = new BMap.Point(MapLng, MapLat);
	var marker = new BMap.Marker(pt );
	baidumap.addOverlay(marker);
</script>
