angular.module('chartUrls').
  factory('ChatService', function ($resource) {
    return {
      crud: $resource('api/chat/:id/:dates',
        {id: '@id'},
        {
          loadAll: {method: 'GET', isArray: true},
          loadById: {method: 'GET', params: {id: '@id'}},
          loadDatesById: {method: 'GET', params: {id: '@id', dates: 'dates'}, isArray: true}
        })
    };
  });