// JavaScript Document

var App = angular.module('App', []);

App.controller('ParserTable', function($scope, $http) {
  $http.get('table.json')
       .then(function(res){
          $scope.table = res.data.table;                
        });
});