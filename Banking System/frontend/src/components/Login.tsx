// Login component
// Task checklist:
// - Build a form with inputs: email, password
// - Validate: email format, password length >= 6
// - On submit: POST /api/user/login with { email, plainPassword }
// - On success: save data.jwtToken to localStorage (key: 'jwtToken')
// - Show loading state and errors from server (use res.text() on non-200)
// - Optionally call props.onLogin(jwt) to inform parent
//
// Example (fetch usage):
// async function handleLogin(e) {
//   e.preventDefault();
//   const res = await fetch('/api/user/login', {
//     method: 'POST', headers: { 'Content-Type': 'application/json' },
//     body: JSON.stringify({ email, plainPassword: password })
//   });
//   if (!res.ok) throw new Error(await res.text());
//   const data = await res.json();
//   localStorage.setItem('jwtToken', data.jwtToken);
// }

export default function Login() {
  return (
    <div>
      {/* TODO: Implement Login form (email, password); submit to backend; store JWT */}
    </div>
  );
} 