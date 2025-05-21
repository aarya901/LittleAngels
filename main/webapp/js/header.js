document.addEventListener("DOMContentLoaded", function () {
	const searchForm = document.getElementById("searchForm");
	const searchInput = document.getElementById("searchInput");

	searchForm.addEventListener("submit", function (e) {
		e.preventDefault(); // prevent actual form submission
		const query = searchInput.value.toLowerCase();
		const products = document.querySelectorAll(".listProduct .item");

		products.forEach((product) => {
			const name = product.querySelector("h2").textContent.toLowerCase();
			if (name.includes(query)) {
				product.style.display = "block";
			} else {
				product.style.display = "none";
			}
		});
	});
});
