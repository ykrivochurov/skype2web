angular.module('chartUrls').
  factory('FeedItemService', function ($resource) {
    return {
      crud: $resource('api/feedItem/:feedItemId',
        {feedItemId: '@feedItemId', chatId: '@chatId', date: '@date'},
        {
          loadByChat: {method: 'GET', params: {chatId: '@chatId', date: '@date'}, isArray: true},
          loadById: {method: 'GET', params: {id: '@id'}, isArray: true}
        })
    };
  });