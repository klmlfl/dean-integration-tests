var assert = require('chai').assert;
var cmd = require('node-cmd');

describe('DEAN-Management', function () {
    describe('Update source', function () {
        it('check for updates', function () {
            cmd.run('git pull');
            assert.equal(-1, [1, 2, 3].indexOf(0));
        });
    });
    describe('#indexPof()', function () {
        it('should poo', function () {
            assert.equal(-1, -1);
        });
    });
});
