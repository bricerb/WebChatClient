angular.module('TIYChocolateApp', [])
   .controller('ChocolateController', function($scope, $http, $timeout) {

        $scope.input = {};

        $scope.messages;

        $scope.getMessages = function() {
            console.log("About to go get me some data!");

            $http.get("/get-message.json")
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.messages = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        $scope.sendMessage = function() {
            console.log("About to add the following text " + JSON.stringify($scope.input));

            $http.post("/message.json", $scope.input)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.messages = response.data;
                        $scope.input = {};
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        $scope.getMessages();

   });