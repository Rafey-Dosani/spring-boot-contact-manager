// Select the theme toggle button
const themeToggleBtn = document.getElementById('theme-toggle');

// Check saved theme in localStorage
const currentTheme = localStorage.getItem('theme');

// Apply saved theme on page load
if(currentTheme === 'dark') {
  document.documentElement.classList.add('dark');
} else {
  document.documentElement.classList.remove('dark');
}

// Update button text based on current theme
if(document.documentElement.classList.contains('dark')) {
  themeToggleBtn.innerHTML = '<i class="fa-solid fa-circle-half-stroke"></i> Light';
} else {
  themeToggleBtn.innerHTML = '<i class="fa-solid fa-circle-half-stroke"></i> Dark';
}

// Toggle dark/light theme on button click
themeToggleBtn.addEventListener('click', () => {
  document.documentElement.classList.toggle('dark');
  
  if(document.documentElement.classList.contains('dark')) {
    localStorage.setItem('theme', 'dark');
    themeToggleBtn.innerHTML = '<i class="fa-solid fa-circle-half-stroke"></i> Light';
  } else {
    localStorage.setItem('theme', 'light');
    themeToggleBtn.innerHTML = '<i class="fa-solid fa-circle-half-stroke"></i> Dark';
  }
});
