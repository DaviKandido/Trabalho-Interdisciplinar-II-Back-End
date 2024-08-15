/** @type {import('../declarations').BuilderFunction} */
function run(responseContent) {
    responseContent = JSON.parse(responseContent);
    let result;

    if (responseContent.candidates &&
        responseContent.candidates.length > 0 &&
        responseContent.candidates[0].content &&
        responseContent.candidates[0].content.parts &&
        responseContent.candidates[0].content.parts.length > 0 &&
        "text" in responseContent.candidates[0].content.parts[0]) {
        result = true;
    } else {
        result = false;
    }

    return result;
}