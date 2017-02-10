angular.module('indexApp', [ 'ngRoute' ]).config(function($routeProvider,$httpProvider) {
	$httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	$routeProvider.when("/", {
		controller : listAward,
		templateUrl : basepath + "/view/award/list"
	}).when("/user", {
		controller : listUser,
		templateUrl : basepath + "/view/user/list"
	}).when("/user/edit/:id", {
		controller : editUser,
		templateUrl : basepath + "/view/user/edit"
	}).when("/award", {
		controller : listAward,
		templateUrl : basepath + "/view/award/list"
	}).when('/award/edit/:id', {
		controller : editAward,
		templateUrl : basepath + "/view/award/edit"
	}).when("/config", {
		controller : listConfig,
		templateUrl : basepath + "/view/config/list"
	}).when('/config/edit/:id', {
		controller : editConfig,
		templateUrl : basepath + "/view/config/edit"
	}).when("/show", {
		controller : listShow,
		templateUrl : basepath + "/view/show/list"
	}).when('/show/edit/:id', {
		controller : editShow,
		templateUrl : basepath + "/view/show/edit"
	}).when("/winner", {
		controller : listWinner,
		templateUrl : basepath + "/view/winner/list"
	}).when('/winner/edit/:id', {
		controller : editWinner,
		templateUrl : basepath + "/view/winner/edit"
	}).when("/vote", {
		controller : listVote,
		templateUrl : basepath + "/view/vote/list"
	}).when('/vote/edit/:id', {
		controller : editVote,
		templateUrl : basepath + "/view/vote/edit"
	}).when("/userVote", {
		controller : listUserVote,
		templateUrl : basepath + "/view/userVote/list"
	}).when('/userVote/edit/:id', {
		controller : editUserVote,
		templateUrl : basepath + "/view/userVote/edit"
	}).otherwise({
		redirectTo : "/"
	})
})
	


