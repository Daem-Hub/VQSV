// Shared helper: loads prototype/data/bootstrap.js so VM-based tests see the same data as the page
// (the battle reads decoded VQSV6 stats from window.VQSVBootstrap.vqsv6Stats at startup).
const fs = require('fs');
const path = require('path');
const vm = require('vm');
const sandbox = { window: {} };
vm.runInNewContext(fs.readFileSync(path.join(__dirname, '..', 'prototype', 'data', 'bootstrap.js'), 'utf8'), sandbox);
module.exports = sandbox.window.VQSVBootstrap;
