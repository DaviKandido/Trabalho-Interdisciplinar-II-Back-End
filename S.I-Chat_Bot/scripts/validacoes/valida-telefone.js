/** @type {import('../declarations').BuilderFunction} */
function run(input) {
    var value = input.replace(/[^\d]/g, '');

    if (value.length !== 11) {
        return false;
    }

    if (value[2] !== '9') {
        return false;
    }

    return value;
}