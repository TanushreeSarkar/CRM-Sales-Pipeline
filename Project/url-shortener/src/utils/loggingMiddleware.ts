interface LogParams {
  stack: string;
  level: 'info' | 'warn' | 'error';
  package: string;
  message: string;
}

const Log = async ({ stack, level, package: pkg, message }: LogParams) => {
  try {
    // Mock API call to Test Server (replace with actual endpoint from Pre-Test Setup)
    await fetch('https://test-server.com/log', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ stack, level, pkg, message }),
    });
  } catch (error) {
    // Silent fail as per no console logging rule
  }
};

export default Log;