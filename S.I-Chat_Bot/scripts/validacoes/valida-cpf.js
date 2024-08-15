/** @type {import('../declarations').BuilderFunction} */
function run(input) {
    var value = input.replace(/[^\d]/g, '');

    if (value.length !== 11) {
        return false;
    }

    if (!Array.from(value).filter(e => e !== value[0]).length) {
        return false;
    }

    return value;
}