const themeToggleBtn = document.getElementById('theme-toggle');
const htmlEl = document.documentElement; // <html> element

// Check if user has saved preference
if (localStorage.getItem('theme') === 'dark') {
  htmlEl.classList.add('dark');
}

themeToggleBtn.addEventListener('click', () => {
  htmlEl.classList.toggle('dark');

  // Save user preference
  if (htmlEl.classList.contains('dark')) {
    localStorage.setItem('theme', 'dark');
    themeToggleBtn.innerHTML = '<i class="fa-solid fa-circle-half-stroke"></i> Light';
  } else {
    localStorage.setItem('theme', 'light');
    themeToggleBtn.innerHTML = '<i class="fa-solid fa-circle-half-stroke"></i> Dark';
  }
});
