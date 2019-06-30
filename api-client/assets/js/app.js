var cards;
var grid;
var app;

var MAPS_KEY = 'google-maps-api-key';

(function () {
    'use strict';

    app = angular.module('networksApp', ['ngRoute', 'ngAnimate']);
    app.config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'assets/parts/home.html',
                controller: 'networksController'
            })
            .when('/comments/:id/', {
                templateUrl: 'assets/parts/comments.html',
                controller: 'commentsController'
            })
            .when('/add', {
                templateUrl: 'assets/parts/addNetwork.html',
                controller: 'addNetworkController'
            })
            .when('/edit/:id', {
                templateUrl: 'assets/parts/editNetwork.html',
                controller: 'editNetworkController'
            })
            .when('/edit/:id/password', {
                templateUrl: 'assets/parts/addPassword.html',
                controller: 'addPasswordController'
            });
    });

    app.controller('networksController', function ($scope, $http) {
        $scope.networks = {};
        cards = document.getElementById('cards');
        grid = new Masonry(cards, {
            itemSelector: 'article'
        });

        $http.get('http://193.191.187.14:10228/api/networks')
            .success(function (data) {
                $scope.networks = data;
            })
            .error(function (response, status) {
                alert('Error reaching API (' + status + ')\nCheck console for details.');
                console.log(response);
            });

        $scope.vote = function (id, action) {
            $http({
                url: 'http://193.191.187.14:10228/api/passwords/' + id,
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: {action: action}
            })
                .success(function () {
                    angular.forEach($scope.networks, function (network) {
                        angular.forEach(network.passwords, function (password) {
                            if( password.id == id )
                                if(action === "upvote")
                                    password.score++;
                                else if(action === "downvote")
                                    password.score--;
                        })
                    })
                })
                .error(function (response, status) {
                    alert('Error reaching API (' + status + ')\nCheck console for details.');
                    console.log(response);
                });
        };

        setTimeout(function () {
            grid.reloadItems();
            grid.layout();
        }, 500);
    });

    app.controller('commentsController', function ($scope, $http, $routeParams) {
            $scope.network = {};
            $scope.form = {};
            $http.get('http://193.191.187.14:10228/api/networks/' + $routeParams.id)
                .success(function (data) {
                    $scope.network = data;
                    $scope.network.hasComments = ($scope.network.comments.length > 0);
                })
                .error(function (response, status) {
                    alert('Error reaching API (' + status + ')\nCheck console for details.');
                    console.log(response);
                });

            $scope.processForm = function () {
                $http({
                    method: 'POST',
                    url: 'http://193.191.187.14:10228/api/comments/' + $routeParams.id,
                    data: JSON.stringify($scope.form),
                    dataType: 'json',
                    headers: {'Content-Type': 'application/json'}
                })
                    .success(function (data) {
                        //$scope.network = data;
                        $scope.network.comments.push({
                            comment: $scope.form.message, timestamp: 'seconds ago'
                        });
                        $scope.errors = {};
                        $scope.form = {};
                    })
                    .error(function (data) {
                        console.log(data);
                        $scope.errors = data.errors;
                    });
            };

        }
    );

    app.controller('addNetworkController', function ($scope, $http, $location) {
        $scope.network = {};
        $scope.errors = {};
        $scope.processForm = function () {
            var geo = getLatLon($scope);
            $scope.network.location.lat = geo['lat'];
            $scope.network.location.lon = geo['lon'];
            $http({
                method: 'POST',
                url: 'http://193.191.187.14:10228/api/networks/' + ( $scope.network.hasPassword ? 'protected' : 'open' ),
                data: JSON.stringify($scope.network),
                dataType: 'json',
                headers: {'Content-Type': 'application/json'}
            })
                .success(function () {
                    $scope.errors = {};
                    $location.path('/');
                })
                .error(function (data) {
                    console.log(data);
                    $scope.errors = data.errors;
                });
        };
    });

    app.controller('editNetworkController', function ($scope, $http, $routeParams, $location) {
        var oldNetwork, newNetwork;
        $scope.network = {};
        $scope.errors = {};
        $http.get('http://193.191.187.14:10228/api/networks/' + $routeParams.id)
            .success(function (data) {
                $scope.network = data;
                oldNetwork = $scope.network.passwords ? true : false;
                if (oldNetwork) {
                    $scope.network.password = ($scope.network.topPassword.password);
                    $scope.network.hasPassword = true;
                }
            })
            .error(function (response, status) {
                alert('Error reaching API (' + status + ')\nCheck console for details.');
                console.log(response);
            });


        $scope.processForm = function () {
            newNetwork = $scope.network.hasPassword;
            var geo = getLatLon($scope);
            $scope.network.location.lat = geo['lat'];
            $scope.network.location.lon = geo['lon'];

            if (newNetwork == oldNetwork) {
                $http({
                    method: 'PUT',
                    url: 'http://193.191.187.14:10228/api/networks/' + ( $scope.network.hasPassword ? 'protected' : 'open' ) + '/' + $routeParams.id,
                    data: JSON.stringify($scope.network),
                    dataType: 'json',
                    headers: {'Content-Type': 'application/json'}
                })
                    .success(function () {
                        $scope.errors = {};
                        $location.path('/');
                    })
                    .error(function (data) {
                        console.log(data);
                        $scope.errors = data.errors;
                    });
            } else {
                $http({
                    method: 'POST',
                    url: 'http://193.191.187.14:10228/api/networks/' + ( $scope.network.hasPassword ? 'protected' : 'open' ),
                    data: JSON.stringify($scope.network),
                    dataType: 'json',
                    headers: {'Content-Type': 'application/json'}
                })
                    .success(function () {
                        $scope.errors = {};
                        $scope.removeNetwork();
                    })
                    .error(function (data) {
                        console.log(data);
                        $scope.errors = data.errors;
                    });
            }
        };

        $scope.removeNetwork = function () {
            $http({
                url: 'http://193.191.187.14:10228/api/networks/' + $routeParams.id,
                method: 'DELETE'
            })
                .success(function () {
                    $location.path('/');
                })
                .error(function (response, status) {
                    alert('Error reaching API (' + status + ')\nCheck console for details.');
                    console.log(response);
                });
        };

    });

    app.controller('addPasswordController', function ($scope, $http, $routeParams, $location) {
        $scope.network = {};
        $scope.errors = {};
        $scope.processForm = function () {
            $http({
                method: 'POST',
                url: 'http://193.191.187.14:10228/api/networks/protected/' + $routeParams.id + '/passwords',
                data: JSON.stringify($scope.network),
                dataType: 'json',
                headers: {'Content-Type': 'application/json'}
            })
                .success(function () {
                    $scope.errors = {};
                    $location.path('/');
                })
                .error(function (data) {
                    console.log(data);
                    $scope.errors = data.errors;
                });
        };
    });
})();


function getLatLon($scope) {
    var address = $scope.network.location.address + ", "
        + $scope.network.location.zip + " "
        + $scope.network.location.city + ", "
        + $scope.network.location.country;
    var latLon = new Array(2);
    var uri = 'https://maps.googleapis.com/maps/api/geocode/json?address=' + address + '&key=' + MAPS_KEY;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', uri, false);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var response = JSON.parse(xhr.responseText);
                latLon['lat'] = response.results[0].geometry.location.lat;
                latLon['lon'] = response.results[0].geometry.location.lng;
            } else {
                latLon['lat'] = 0;
                latLon['lon'] = 0;
            }
        }
    };
    xhr.send();
    return latLon;
}