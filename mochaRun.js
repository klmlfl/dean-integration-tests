var assert = require('chai').assert;
var cmd = require('node-cmd');

var moof = "ded"


describe('Update sources', function() {


    it('check for updates and install dependencies: dean-integration-tests', function(done) {
        var output = "data";
        var output2 = "data2";

        var outputCB = function(data) {
            output = data;
        };

        var outputCB2 = function(data) {
            output2 = data;
        };

        cmd.run('cd /Users/srahman/Repos/dean-integration-tests/');
        cmd.get('git pull',function(data){
            outputCB(data);
            assert.equal(output.replace(/(\r\n|\n|\r)/gm,"" /*remove newline in output*/), 'Already up-to-date.');
            done()
        });
        cmd.get('npm install',function(data){
            outputCB2(data);
            assert.equal(true, true);
        });
    });



    it('check for updates and install dependencies: event-aggregator', function(done) {
        var output = "data";
        var output2 = "data2";

        var outputCB = function(data) {
            output = data;
        };

        var outputCB2 = function(data) {
            output2 = data;
        };

        cmd.run('cd /Users/srahman/Repos/event-aggregator/');
        cmd.get('git pull',function(data){
            outputCB(data);
            assert.equal(output.replace(/(\r\n|\n|\r)/gm,"" /*remove newline in output*/), 'Already up-to-date.');
            done()
        });
        cmd.get('npm install',function(data){
            outputCB2(data);
            assert.equal(true, true);
            done()
        });
    });



    it('check for and install updates: olap-event-consumer', function(done) {
        var output = "data";
        var output2 = "data2";

        var outputCB = function(data) {
            output = data;
        };

        var outputCB2 = function(data) {
            output2 = data;
        };

        cmd.run('cd /Users/srahman/Repos/olap-event-consumer/');
        cmd.get('git pull',function(data){
            outputCB(data);
            assert.equal(output.replace(/(\r\n|\n|\r)/gm,"" /*remove newline in output*/), 'Already up-to-date.');
            done()
        });
        cmd.get('npm install',function(data){
            outputCB2(data);
            assert.equal(true, true);
            done()
        });
    });



});

describe('Start processes', function() {


    it('event-aggregator process', function(done) {
        var output = "data";

        var outputCB = function(data) {
            output = data;
        };

        cmd.run('cd /Users/srahman/Repos/event-aggregator/');
        cmd.get('env DEAN_HOME=/Users/srahman/Repos/ DEAN_ENV=demo node event-aggregator.js',function(data){
            outputCB(data);
            done()
        });
    });


    it('olap-event-consumer process', function(done) {
        var output = "data";

        var outputCB = function(data) {
            output = data;

        };

        cmd.run('cd /Users/srahman/Repos/event-aggregator/');
        cmd.get('env DEAN_HOME=/Users/srahman/Repos/ DEAN_ENV=demo node olap-event-consumer.js',function(data){
            outputCB(data);
            done()

        });
    });



});

