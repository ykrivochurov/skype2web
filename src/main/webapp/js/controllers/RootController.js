function RootController($scope) {
  $scope.pageName;
  $scope.dataLoaded = false;

  $scope.setPageName = function (name) {
    $scope.pageName = name;
  };

  function safeApply() {
    $scope.dataLoaded = true;
    if (!$scope.$$phase) {
      $scope.$apply();
    }
  }

  safeApply();
}