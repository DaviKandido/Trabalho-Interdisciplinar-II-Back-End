/** @type {import('../declarations').BuilderFunction} */
function run(input) {
	input = JSON.parse(input);

	const text = input.candidates[0].content.parts[0].text;

	return text;
}